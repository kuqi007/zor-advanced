package com.zor.basic.collection.list;

import java.util.ArrayList;
import java.util.List;

public class ToArraySpeedTest {
    private static final int COUNT = 100 * 100 * 100;

    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();
        //构造一个100万个元素的测试集合
        for (int i = 0; i < COUNT; i++) {
            list.add(i * 1.0);
        }

        long start = System.nanoTime();

        Double[] notEmptyArray = new Double[COUNT - 1];
        list.toArray(notEmptyArray);

        long middle1 = System.nanoTime();

        Double[] equalArray = new Double[COUNT];
        list.toArray(equalArray);

        long middle2 = System.nanoTime();

        Double[] doubleArray = new Double[COUNT * 2];
        list.toArray(doubleArray);
        long end = System.nanoTime();

        long notEnoughArrayTime = middle1 - start;
        long equalArrayTime = middle2 - middle1;
        long doubleArrayTime = end - middle2;


        //
        System.out.println("数组容量小于集合大小：notEnoughArrayTime：" + notEnoughArrayTime / (1000.0 * 1000.0) + " ms");
        //等于最快
        System.out.println("数组容量等于集合大小：notEnoughArrayTime：" + equalArrayTime / (1000.0 * 1000.0) + " ms");
        //
        System.out.println("数组容量大于集合大小：notEnoughArrayTime：" + doubleArrayTime / (1000.0 * 1000.0) + " ms");

    }
}
