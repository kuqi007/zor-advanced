package com.zor.advanced.consistency.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zqq
 * @date 2021/3/17
 */
public abstract class AbstractCluster implements Cluster {
    private static MessageDigest md5 = null;
    protected List<Node> nodes = new ArrayList<>();

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("no md5 algorithm found");
        }
    }

    @Override
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * 实现一致性哈希算法中使用的哈希函数，使用MD5算法来保证一致性哈希的平衡性
     *
     * @param key key
     * @return hash值
     */
    public long hash(String key) {
        md5.reset();
        md5.update(key.getBytes());
        byte[] bKey = md5.digest();
        // 具体的哈希函数实现细节--每个字节 & 0xFF 再移位
        // TODO 这太复杂了吧。。。
        long result = ((long) (bKey[3] & 0xFF) << 24)
                | ((long) (bKey[2] & 0xFF) << 16
                | ((long) (bKey[1] & 0xFF) << 8)
                | (long) (bKey[0] & 0xFF));
        return result & 0xffffffffL;
    }


}
