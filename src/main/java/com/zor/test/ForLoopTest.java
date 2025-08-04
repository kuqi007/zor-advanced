package com.zor.test;

public class ForLoopTest {
    public static void main(String[] args) {
        testBreak();
    }

    public static void testContinue() {
        // 输出：0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3,  >>> OK
        // 特殊用法
        retry:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(j + ", ");
                if (j == 3) {
                    continue retry; //此处加retry，相当于继续外层的循环
                }
            }
        }
        System.out.print(" >>> OK");
    }

    public static void testBreak() {
        // 输出：0, 1, 2, 3,  >>> OK
        retry:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(j + ", ");
                if (j == 3) {
                    break; //此处加retry，相当于退出外面的循环，如果不加retry，只退出内层循环
                }
            }
        }
        System.out.print(" >>> OK");
    }
}
