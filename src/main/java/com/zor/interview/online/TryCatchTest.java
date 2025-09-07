package com.zor.interview.online;

/**
 * 输出结果
 */
public class TryCatchTest {

    public static void main(String[] args) {
        //System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(test());
    }

    private static int test() {
        int temp = 1;
        try {
            System.out.println(temp);
            return temp++;
        } catch (Exception e) {
            System.out.println(temp);
            return temp++;
        } finally {
            temp++;
            System.out.println(temp);
            //return temp;
        }
    }
}
