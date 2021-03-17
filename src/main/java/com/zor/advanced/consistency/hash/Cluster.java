package com.zor.advanced.consistency.hash;

import java.util.List;

/**
 * @author zqq
 * @date 2021/3/17
 */
public interface Cluster {

    /**
     * 添加一个节点
     *
     * @param node 新节点
     */
    void addNode(Node node);

    /**
     * 删除节点
     */
    default void removeNode(Node node) {
        this.removeNode(node.getName());
    }

    /**
     * 通过名称删除一个节点
     *
     * @param nodeName 节点名称
     */
    void removeNode(String nodeName);

    Node get(String key);

    List<Node> getNodes();

}
