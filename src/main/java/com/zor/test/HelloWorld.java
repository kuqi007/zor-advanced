package com.zor.test;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("HelloWorld");

        HelloWorld test=new HelloWorld();
        System.out.println(test.test(10));


    }


    String test(int length) {
        String result = "";

        List<Integer> items = new ArrayList<>();

        for (int i = 0; i < length; i++) {

            items.add(i);

        }


        for (int i = 0; i < items.size(); i++) {

            items.remove(i);

            result += items.get(i).toString();


        }

        return result;//答案是13579


    }

}
