package com.zor.advanced.consistency.hash.test;

import com.zor.advanced.consistency.hash.Cluster;
import com.zor.advanced.consistency.hash.ConsistentHashCluster;
import com.zor.advanced.consistency.hash.Node;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @author zqq
 * @date 2021/3/17
 */
public class ConsistentHashClusterTest {

    @Test
    public void test() {
        Cluster cluster = new ConsistentHashCluster();
        cluster.addNode(new Node("node1", "192.168.16.1", "7001"));
        cluster.addNode(new Node("node2", "192.168.16.2", "7002"));
        cluster.addNode(new Node("node3", "192.168.16.3", "7003"));
        cluster.addNode(new Node("node4", "192.168.16.4", "7004"));

        int dataCount = 2000;
        String preKey = "Data_";

        for (int index = 0; index < dataCount; index++) {
            String key = preKey + index;
            Node node = cluster.get(key);
            node.put(key, "测试");
        }

        System.out.println("数据分布情况：");
        cluster.getNodes().forEach(node -> {
            System.out.println("Node name：" + node.getName() + ", 数据量" + node.dataSize());
        });

        // 缓存命中率
        long hitCount = IntStream.range(0, dataCount)
                .filter(index -> cluster.get(preKey + index).get(preKey + index) != null)
                .count();
        System.out.println("正常情况命中率：" + 1.0 * hitCount / dataCount);

        // 添加一个节点后测试
        //cluster.addNode(new Node("node5", "192.168.16.5", "7005"));
        hitCount = IntStream.range(0, dataCount)
                .filter(index -> cluster.get(preKey + index).get(preKey + index) != null)
                .count();
        System.out.println("添加节点node5之后命中率：" + 1.0 * hitCount / dataCount);


        // 删除某一个节点后测试
        cluster.removeNode("node3");
        hitCount = IntStream.range(0, dataCount)
                .filter(index -> cluster.get(preKey + index).get(preKey + index) != null)
                .count();
        System.out.println("删除节点node3之后命中率：" + 1.0 * hitCount / dataCount);

    }


}
