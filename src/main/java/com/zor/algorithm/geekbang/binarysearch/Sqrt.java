package com.zor.algorithm.geekbang.binarysearch;

/**
 * 求一个数的平方根，要求精确到小数点后 6 位
 * Created by kuqi0 on 2021/5/7
 */
public class Sqrt {
    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        double a = 6;
        double num = sqrt.sqrt(a, 0.000001);
        System.out.println("算法结果：" + num);
        System.out.println("实际结果：" + Math.sqrt(a));

    }

    public double sqrt(double a, double precision) {
        double low, high, mid, tmp;
        if (a > 1) {
            low = 1;
            high = a;
        } else {
            low = a;
            high = 1;
        }

        while (low <= high) {
            mid = (low + high) / 2.000;
            tmp = mid * mid;
            if (tmp < precision + 1 && tmp >= a - precision) {
                return mid;
            } else if (tmp > a) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return -1.000;
    }
}
