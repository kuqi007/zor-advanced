package com.zor.algorithm.interview.online.bytedance;


/**
 * 有序数组，[1, 2, 3, 4, 4, 4,4, 5, 6, 7]
 * 给出一个数字，求在数组中出现的次数。
 * 时间复杂度要尽量低
 */
public class GetCount {
    public static void main(String[] args) {
        //System.out.print("请输入数字：");
        //Scanner in = new Scanner(System.in);
        //int cal = in.nextInt();
        //System.out.println("要查找的数为：" + cal);
        int[] arr = {1, 2, 3, 3, 3, 4, 4, 4, 4, 5, 6, 7};
        int count = getCount(arr, 6);
        System.out.println("出现的次数为：" + count);
    }

    private static int getCount(int[] nums, int cal) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > cal) {
                right = mid - 1;
            } else if (nums[mid] < cal) {
                left = mid + 1;
            } else {
                int n = 0;
                int i = mid - 1, j = mid;
                while (i >= 0 && nums[i] == cal) {
                    i--;
                    n++;
                }
                while (j <= nums.length - 1 && nums[j] == cal) {
                    j++;
                    n++;
                }
                return n;
            }
        }
        return -1;
    }
}