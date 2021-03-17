package com.zor.advanced.consistency.hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性哈希算法实现（包含虚拟节点）
 *
 * @author zqq
 * @date 2021/3/17
 */
public class ConsistentHashCluster extends AbstractCluster {
    private static final int DEFAULT_VNODE_NUM = 150;
    /**
     * 虚拟节点数量
     */
    private final int vNodeNum;
    /**
     * 虚拟节点
     */
    private final SortedMap<Long, Node> vNodes = new TreeMap<>();

    public ConsistentHashCluster() {
        this(DEFAULT_VNODE_NUM);
    }

    public ConsistentHashCluster(int vNodeNum) {
        this.vNodeNum = vNodeNum;
    }

    /**
     * 添加一个节点
     *
     * @param node 新节点
     */
    @Override
    public void addNode(Node node) {
        this.nodes.add(node);
        for (int i = 0; i < vNodeNum; i++) {
            String vNodeName = node.getName() + "_vnode_" + i;
            vNodes.put(hash(vNodeName), node);
        }
    }

    /**
     * 通过名称删除一个节点
     *
     * @param nodeName 节点名称
     */
    @Override
    public void removeNode(String nodeName) {
        this.nodes.removeIf(node -> node.getName().equals(nodeName));
        for (int i = 0; i < vNodeNum; i++) {
            String vNodeName = nodeName + "_vnode_" + i;
            vNodes.remove(hash(vNodeName));
        }

    }

    @Override
    public Node get(String key) {
        long hash = hash(key);
        // 返回大于等于当前hash值的map子集
        SortedMap<Long, Node> subMap = vNodes.tailMap(hash);
        // 如果不为空，那么取子集中的第一个元素
        if (!subMap.isEmpty()) {
            return subMap.get(subMap.firstKey());
        }
        // 如果子集为空就需要把TreeMap的第一个元素返回
        return vNodes.get(vNodes.firstKey());
    }
}
