package com.zor.algorithm.search;

/**
 * Created by zqq on 2019/12/19.
 */
public class BinarySearch {

    public static void main(String[] args) {

        //必须保证有序
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 8, 9, 11, 20};

        int index;
        BinarySearch binarySearch = new BinarySearch();
        System.out.println("递归写法：");
        index = binarySearch.recursive(arr, 20, 0, arr.length - 1);
        System.out.println(index);

        System.out.println("循环写法：");
        index = binarySearch.wileLoop(arr, 8);
        System.out.println(index);
    }

    //递归写法
    private int recursive(int[] arr, int target, int start, int end) {
        int mid = start + (end - start) / 2;
        if (start > end) {
            //当start下标大于end的时候说明找不到
            return -1;
        } else {
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                //往左找
                return recursive(arr, target, start, mid - 1);
            } else {
                //往右找
                return recursive(arr, target, mid + 1, end);
            }
        }
    }

    //非递归写法
    private int wileLoop(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }


}
