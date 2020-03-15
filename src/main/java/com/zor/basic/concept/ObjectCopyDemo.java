package com.zor.basic.concept;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 对象复制问题
 */
public class ObjectCopyDemo {

    public static void main(String[] args) {
        Subject subject = new Subject("aaa");

        Student studentA = new Student();
        studentA.subject = subject;

        Subject subject1 = studentA.subject;
        // 此时subject1是aaa
        System.out.println(subject1);

        studentA.subject.name = "bbb";
        //  此时studentA的subject引用不变，里面值发生了改变，所以subject1变成了bbb
        System.out.println(subject1);

        // 此时studentA的subject指向了另一个新的引用，所以subject1不会发生改变，仍然是bbb，不会变成ccc
        studentA.subject = new Subject("ccc");

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



