package com.zor.advanced.consistency.hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性哈希算法简单实现，不包含虚拟节点，数据倾斜比较严重
 *
 * @author zqq
 * @date 2021/3/17
 */
public class ConsistentHashPlainCluster extends AbstractCluster {

    /**
     * 哈希环
     */
    private final SortedMap<Long, Node> circle = new TreeMap<>();

    /**
     * 添加一个节点
     *
     * @param node 新节点
     */
    @Override
    public void addNode(Node node) {
        this.nodes.add(node);
        long hash = hash(node.getName());
        circle.put(hash, node);
    }

    /**
     * 通过名称删除一个节点
     *
     * @param nodeName 节点名称
     */
    @Override
    public void removeNode(String nodeName) {
        this.nodes.removeIf(node -> node.getName().equals(nodeName));
        circle.remove(hash(nodeName));
    }

    @Override
    public Node get(String key) {
        long hash = hash(key);
        SortedMap<Long, Node> subMap = circle.tailMap(hash);
        if (!subMap.isEmpty()) {
            return subMap.get(subMap.firstKey());
        }
        return circle.get(circle.firstKey());
    }
}
