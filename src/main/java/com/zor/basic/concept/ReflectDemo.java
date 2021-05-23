package com.zor.basic.concept;

import java.lang.reflect.Field;

/**
 * 反射测试
 * Created by kuqi0 on 2021/5/23
 */
public class ReflectDemo {

    public static void main(String[] args) throws Exception, IllegalAccessException, NoSuchFieldException {

        Apple apple = new Apple();
        apple.setPrice(5);
        System.out.println("apple price" + apple.getPrice());

        Field price = Apple.class.getDeclaredField("price");
        price.setAccessible(true);
        price.set(apple, 10);

        System.out.println("修改之后的价格为" + apple.getPrice());

        //
        //Class<?> clz = Class.forName("com.zor.basic.concept.Apple");
        //Field[] declaredFields = clz.getDeclaredFields();
        //for (Field declaredField : declaredFields) {
        //    if (declaredField.getName().equals("price")) {
        //        declaredField.setAccessible(true);
        //        declaredField.set(apple, 4);
        //    }
        //}
        //
        //
        //System.out.println(declaredFields);

    }


}
