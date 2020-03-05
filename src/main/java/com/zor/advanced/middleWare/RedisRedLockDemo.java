package com.zor.advanced.middleWare;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * https://yq.aliyun.com/articles/674394
 * 为了取到锁，客户端应该执行以下操作:
 * <p>
 * 获取当前Unix时间，以毫秒为单位。
 * <p>
 * 依次尝试从5个实例，使用相同的key和具有唯一性的value（例如UUID）获取锁。当向Redis请求获取锁时，
 * 客户端应该设置一个网络连接和响应超时时间，这个超时时间应该小于锁的失效时间。例如你的锁自动失效时间为10秒，
 * 则超时时间应该在5-50毫秒之间。这样可以避免服务器端Redis已经挂掉的情况下，
 * 客户端还在死死地等待响应结果。如果服务器端没有在规定时间内响应，客户端应该尽快尝试去另外一个Redis实例请求获取锁。
 * <p>
 * 客户端使用当前时间减去开始获取锁时间（步骤1记录的时间）就得到获取锁使用的时间。
 * 当且仅当从大多数（N/2+1，这里是3个节点）的Redis节点都取到锁，并且使用的时间小于锁失效时间时，锁才算获取成功。
 * <p>
 * 如果取到了锁，key的真正有效时间等于有效时间减去获取锁所使用的时间（步骤3计算的结果）。
 * <p>
 * 如果因为某些原因，获取锁失败（没有在至少N/2+1个Redis实例取到锁或者取锁时间已经超过了有效时间），
 * 客户端应该在所有的Redis实例上进行解锁（即便某些Redis实例根本就没有加锁成功，防止某些节点获取到锁但是客户端没有得到响应而导致接下来的一段时间不能被重新获取锁）。
 */
public class RedisRedLockDemo {
    public static void main(String[] args) {
        Config config = new Config();

        config.useSentinelServers().addSentinelAddress("127.0.0.1:6369", "127.0.0.1:6379", "127.0.0.1:6389")

                .setMasterName("masterName")

                .setPassword("password").setDatabase(0);

        RedissonClient redissonClient = Redisson.create(config);

        // 还可以getFairLock(), getReadWriteLock()

        RLock redLock = redissonClient.getLock("REDLOCK_KEY");

        boolean isLock;

        try {

            //isLock = redLock.tryLock();

            // 500ms拿不到锁, 就认为获取锁失败。10000ms即10s是锁失效时间。

            isLock = redLock.tryLock(500, 10000, TimeUnit.MILLISECONDS);

            if (isLock) {

                //TODO if get lock success, do something;

            }

        } catch (Exception e) {
            //do sth
        } finally {

            // 无论如何, 最后都要解锁

            redLock.unlock();

        }
    }
}
