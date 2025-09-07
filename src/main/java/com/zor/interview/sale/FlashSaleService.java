package com.zor.interview.sale;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 2025年8月初Mercury面试。。。
 *
 * 假设你正在为一家电商平台设计并实现一个限时抢购（秒杀）系统的核心服务。该服务需要处理用户对某件商品的抢购请求。这个系统必须具备高并发处理能力、数据一致性保证以及良好的性能。
 * 请使用Java的内部工具类模拟分布式环境下的数据库、缓存、锁等等。
 * FlashSaleService
 * initStock()
 * getStock()
 * purchase()
 */
public class FlashSaleService {
    Map<Long, AtomicLong> stockMap = new ConcurrentHashMap<>();
    // 模拟分布式锁
    Map<Long, Object> stockLock = new ConcurrentHashMap<>();
    // 幂等处理
    Map<String, Long> requestMap = new ConcurrentHashMap<>();

    // 添加定时清理任务用于清理过期的 requestId
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public FlashSaleService() {
        // 每隔10s清理一次过期的requestID
        scheduler.scheduleAtFixedRate(() -> {
            long currentTimeMillis = System.currentTimeMillis();
            requestMap.entrySet().removeIf(entry -> currentTimeMillis > entry.getValue());
        }, 10, 10, TimeUnit.SECONDS);
    }

    // 初始化库存
    public Boolean initStock(Long productId, Long stock) {

        stockLock.putIfAbsent(productId, new Object());

        synchronized (stockLock.get(productId)) {
            AtomicLong curStock = stockMap.computeIfAbsent(productId, k -> new AtomicLong(0));
            curStock.set(stock);
            stockMap.put(productId, curStock);
        }

        return true;
    }

    public Long getStock(Long productId) {
        AtomicLong stock = stockMap.get(productId);
        return stock == null ? 0L : stock.get();
    }

    public Boolean purchase(Long userId, Integer count, Long productId, String requestId) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        // 幂等性检查，检查requestId是否存在且未超时
        if (requestMap.containsKey(requestId)) {
            Long expireTime = requestMap.get(requestId);
            if (currentTimeMillis < expireTime) {
                // 请求未超时，拒绝请求
                return false;
            }
        }
        // 记录当前请求过期时间（10s后）
        requestMap.put(requestId, currentTimeMillis + 10 * 1000);

        // 如果为空，则初始化一个空的锁对象
        stockLock.computeIfAbsent(productId, k -> new Object());
        try {
            synchronized (stockLock.get(productId)) {
                AtomicLong curStock = stockMap.get(productId);

                if (curStock.get() < count) {
                    return false;//抢购失败
                }
                curStock.addAndGet(-count);
                return true;
            }
        } catch (Exception e) {
            //
            return false;
        }

        // 注意：requestId 不会立即移除，而是由定时任务清理过期的条目。


    }

    // 关闭清理任务（可选，用于优雅关闭）
    public void shutdown() {
        scheduler.shutdown();
    }


}
