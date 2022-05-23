package com.zor.algorithm.geekbang.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by kuqi0 on 2022/5/23
 */
public class QuickSortIII {

    public static void main(String[] args) {
        QuickSortIII quickSort = new QuickSortIII();
        int[] nums = {2, 8, 4, 7, 1, 0, 6};
        quickSort.quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }


    private void quickSort(int[] nums, int p, int r) {
        // 当区间缩小为1时，代表有序
        if (p >= r) return;

        int q = selectPivotPartition(nums, p, r);
        quickSort(nums, 0, q - 1);
        quickSort(nums, q + 1, r);
    }

    /**
     * 为什么需要随机化分区？
     * 如果数组已经是有序的，比如 1,3,5,6,8这种，每次分区得到的两个区间都是不均等的，我们需要进行大约n次分区操作，
     * 每次分区我们平均要扫描n/2个元素，这种情况下，快排的时间复杂度从O(nlogn) 退化成了 O(n^2)
     */
    private int randomPartition(int[] nums, int p, int r) {
        Random random = new Random();
        int i = random.nextInt(r - p + 1) + p;
        swap(nums, i, r);
        return partition(nums, p, r);
    }

    /**
     * 三数取中法
     * 从首、尾、中间各取一个数，取中间值作为分区点
     */
    private int selectPivotPartition(int[] nums, int p, int r) {
        int mid = p + (r - p) / 2;
        int e1 = nums[p], e2 = nums[mid], e3 = nums[r];
        int q = r;
        if (e1 > e2 && e1 < e3 || (e1 > e3 && e1 < e2)) {
            q = p;
        } else if (e2 > e1 && e2 < e3 || (e2 > e3 && e2 < e1)) {
            q = mid;
        }
        swap(nums, q, r);
        return partition(nums, p, r);
    }


    private int partition(int[] nums, int p, int r) {
        // 选举一个数当基准，这里选择最后一个数
        int pivot = nums[r];
        // p到i代表是已处理区间
        // j到r代表是未处理区间，j是当前比较的数，如果nums[j]小于pivot，那么交换i和j，把小的数换到[p,i]区间
        int i = p;
        for (int j = p; j < r; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }

        swap(nums, i, r);
        return i;
    }


    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }


}
