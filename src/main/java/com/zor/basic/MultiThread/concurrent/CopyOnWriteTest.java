package com.zor.basic.MultiThread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <20 ; i++) {
            list.add(i);
        }

        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>(list);

        for (Integer integer : copyOnWriteArrayList) {
            copyOnWriteArrayList.add(111);
        }

        System.out.println(copyOnWriteArrayList);

    }
}
