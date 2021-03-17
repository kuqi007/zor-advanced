package com.zor.advanced.consistency.hash;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 真实节点
 *
 * @author zqq
 * @date 2021/3/17
 */
public class Node {
    private final String name;
    private final String host;
    private final String port;
    private final Map<String, Object> data = new ConcurrentHashMap<>();

    public Node(String name, String host, String port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }

    public void remove(String key) {
        data.remove(key);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public int dataSize() {
        return data.size();
    }
}
