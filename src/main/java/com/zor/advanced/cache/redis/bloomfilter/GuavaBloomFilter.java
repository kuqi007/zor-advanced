package com.zor.advanced.cache.redis.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * @author zqq
 * @date 2021/1/8
 */
public class GuavaBloomFilter {
    public static void main(String[] args) {

        BloomFilter<String> filter = BloomFilter.create(
                Funnels.stringFunnel(Charset.defaultCharset()),
                1500,
                0.01);

        System.out.println(filter.mightContain("test1"));
        System.out.println(filter.mightContain("test2"));

        filter.put("test1");
        filter.put("test2");

        System.out.println(filter.mightContain("test1"));
        System.out.println(filter.mightContain("test2"));

    }

}
