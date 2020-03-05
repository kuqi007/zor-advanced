package com.zor.basic.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class FailFastTest {
    public static void main(String[] args) {
        subListTest();
    }

    private static void subListTest() {
        List<String> masterList = new ArrayList<>();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");
        masterList.add("five");

        List<String> branchList = masterList.subList(0, 3);

        //下方三行代码如果不注释，则会导致branchList操作出现异常ConcurrentModificationException
        //masterList.remove(0);
        //masterList.add("ten");
        //masterList.clear();

        branchList.clear();
        branchList.add("six");
        branchList.add("seven");
        branchList.remove(0);

        for (String t : branchList) {
            System.out.println(t);
        }
        System.out.println(masterList);
    }

    /**
     * for循环直接删除ArrayList中的特定元素是错的，不同的for循环会发生不同的错误，泛型for会抛出 ConcurrentModificationException，
     * 普通的for想要删除集合中重复且连续的元素，只能删除第一个。
     * <p>
     * 错误原因：打开JDK的ArrayList源码，看下ArrayList中的remove方法（注意ArrayList中的remove有两个同名方法，只是入参不同，
     * 这里看的是入参为Object的remove方法）是怎么实现的，一般情况下程序的执行路径会走到else路径下最终调用faseRemove方法,
     * 会执行System.arraycopy方法，导致删除元素时涉及到数组元素的移动。针对普通for循环的错误写法，在遍历第一个字符串b时因为符合删除条件，
     * 所以将该元素从数组中删除，并且将后一个元素移动（也就是第二个字符串b）至当前位置，导致下一次循环遍历时后一个字符串b并没有遍历到，
     * 所以无法删除。针对这种情况可以倒序删除的方式来避免
     * <p>
     * 解决方案：用 Iterator。
     * <p>
     * List<String> list = new ArrayList(Arrays.asList("a", "b", "b" , "c", "d"));
     * Iterator<String> iterator = list.iterator();
     * while(iterator.hasNext()) {
     * String element = iterator.next();
     * if(element.equals("b")) {
     * iterator.remove();
     * }
     */
    @Test
    public void deleteArrayList() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");

        //for (int i = 0; i < list.size(); i++) {
        //如果有两个重复且连续的元素，只能删除第一个。
        //    if(list.get(i).equals("two")){
        //        list.remove(i);
        //    }
        //}

        //删除会报错java.util.ConcurrentModificationException
        for (String s : list) {
            if (s.equals("two")) {
                list.remove(s);
            }
        }

        //使用iterator删除则没问题
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals("two")) {
                iterator.remove();
            }

        }
        System.out.println(list);

    }
}
