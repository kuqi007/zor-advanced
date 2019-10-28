package com.zor.basic.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author zqq
 * @date 2019/7/28
 */
public class ArrayListTest {

    @Test
    public void subListTest(){
        List<String> names = new ArrayList<>();
        names.add("hello");
        names.add("world");
        names.add("though");
        names.add("it");
        names.add("sucks");
        //返回List是没问题的
        //List<String> subList = names.subList(0, 1);
        //System.out.println(subList);

        //强转ArrayList会报错ClassCastException，因为返回的是ArrayList的一个内部类SubList，并没有继承ArrayList
        //ArrayList subList = (ArrayList) names.subList(0, 1);
        //System.out.println(subList);
    }

    //asList测试

    @Test
    public void asListTest1() {
        int[] arr = {1, 2, 3};
        List ints = Arrays.asList(arr);
        System.out.println(ints.size());
        //答案是1（只有一个int类型的数组），因为基础类型无法泛型化
    }

    @Test
    public void asListTest2() {
        String[] arr = {"欢迎", "关注", "Java"};
        List<String> list = Arrays.asList(arr);
        arr[1] = "爱上";
        list.set(2, "我");
        System.out.println(Arrays.toString(arr));
        System.out.println(list.toString());
        //结果是欢迎爱上我，asList产生的集合元素是直接引用作为参数的数组，所以当外部数组或集合改变时，数组和集合会同步变化
    }

    @Test
    public void asListTest3() {
        String[] arr = {"欢迎", "关注", "Java"};
        List<String> list = Arrays.asList(arr);
        list.add("新增");
        list.remove("关注");
        System.out.println(list.toString());
        //由于asList产生的集合并没有重写add,remove等方法，所以它会调用父类AbstractList的方法，而父类的方法中抛出的却是异常信息

    }
}
