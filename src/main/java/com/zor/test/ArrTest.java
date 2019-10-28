package com.zor.test;

import org.junit.Test;

import java.util.Arrays;

public class ArrTest {

    @Test
    public void arrTest() {


        int[] nums = {4, 0, 2, 0, 7};

        for (int j = nums.length - 1, i = nums.length - 1; i > 0; --i) {

            if (nums[i] == 0) {
                int temp = nums[i];//先保留当前值
                nums[i] = nums[j];//将当前值换成最后一个
                nums[j] = temp;//最后一个值换成0
                i--;

            }

        }

        System.out.println(Arrays.toString(nums));

    }

    public static void moveZeroes(int[] nums) {


        //int idx = 0;
        ////把不是0的全部移到前面，idx的个数是不为0的个数少一个
        //for (int num : nums) {
        //    if (num != 0) {
        //        nums[idx++] = num;
        //    }
        //}
        ////后面的
        //while (idx < nums.length) {
        //    nums[idx++] = 0;
        //}

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num != 0) {
                nums[index] = num;
                index++;
            }
        }

        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }


    }


    public static void main(String[] args) {
        moveZeroes(new int[]{0, 1, 0, 3, 12});


    }

    @Test
    public void test() {
        int x = 0;
        int y = 0;
        int k = 0;
        for (int z = 0; z < 5; z++) {
            if ((++x > 2) && (++y > 2) && (k++ > 2)) {//短路与
                x++;
                ++y;
                k++;
            }
        }
        System.out.println(x + "" + y + "" + k);//531
    }

    @Test
    public void test1() {

        int i, sum = 0;
        for (i = 0; i < 10; ++i, sum += i) {

            System.out.println(sum);
        }
    }


}
