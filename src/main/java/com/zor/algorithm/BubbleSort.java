package com.zor.algorithm;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arrays = new int[]{9, 3, 10, 1, 8};
        int temp;
        boolean isChange = true;
        for (int i = 0; i < arrays.length - 1 && isChange; i++) {
            isChange = false;
            for (int j = 0; j < arrays.length - i - 1; j++) {
                if (arrays[j] > arrays[j + 1]) {
                    temp = arrays[j];
                    arrays[j] = arrays[j + 1];
                    arrays[j + 1] = temp;
                    isChange = true;
                    System.out.println(Arrays.toString(arrays));
                }
            }
        }
    }

}
