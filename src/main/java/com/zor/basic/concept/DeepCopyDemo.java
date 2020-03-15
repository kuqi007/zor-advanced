package com.zor.basic.concept;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 静态内部类的对象复制？？？？？？？？
 */
public class DeepCopyDemo {
    public static void main(String[] args) {
        Subject subject = new Subject("aaa");

        Student studentA = new Student();
        studentA.subject = subject;

        Subject subject1 = studentA.subject;
        System.out.println(subject1);

        studentA.subject = new Subject("bbb");

        System.out.println(subject1);


    }

    @ToString
    static class Student {
        private int age;
        private String name;
        private Subject subject;
    }

    @ToString
    @AllArgsConstructor
    static class Subject {
        private String name;
    }
}



