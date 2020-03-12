package com.zor.basic.collection.list;


import java.util.ArrayList;
import java.util.List;

public class ListNoGeneric {
    public static void main(String[] args) {
        List a1 = new ArrayList();
        a1.add(new Object());
        a1.add(new Integer(111));
        a1.add(new String("hello a1a2"));

        List<Object> a2 = a1;
        a2.add(new Object());
        a2.add(new Integer(222));
        a2.add(new String("hello world"));

        List<Integer> a3 = a1;
        a3.add(new Integer(333));
        // 下面两行编译出错，不允许增加非Integer的类型进入集合
        //a3.add(new Object());
        //a3.add(new String("hello wordl"));

        List<?> a4 = a1;
        a1.remove(0);
        a4.clear();
        //编译出错。不允许增加任何元素
        //a4.add(new Object());


    }
}
