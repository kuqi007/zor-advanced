package com.zor.basic.lambda;


import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by zqq on 2019/10/28.
 * <p>
 * https://juejin.im/post/5ce66801e51d455d850d3a4a
 */
public class LambdaTest {

    public static void main(String[] args) {
        Student student = new Student("9龙", 23, 195);
        Predicate<Integer> predicate = x -> x > 185;
        System.out.println("9龙的身高高于185吗？：" + predicate.test(student.getStature()));


        Consumer<String> consumer = System.out::println;
        consumer.accept("命运由我不由天");

        Function<Student, String> function = Student::getName;
        String name = function.apply(student);
        System.out.println(name);


        test(() -> "我是一个演示的函数式接口");

    }

    public static void test(Worker worker) {
        String work = worker.work();
        System.out.println(work);
    }

    public interface Worker {
        String work();
    }
}


class Student {
    private String name;
    private int age;
    private int stature;


    Student(String name, int age, int stature) {
        this.name = name;
        this.age = age;
        this.stature = stature;
    }

    int getAge() {
        return age;
    }

    void setAge(int age) {
        this.age = age;
    }

    int getStature() {
        return stature;
    }

    void setStature(int stature) {
        this.stature = stature;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }


}
