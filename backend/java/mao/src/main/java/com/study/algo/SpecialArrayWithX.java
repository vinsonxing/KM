package com.study.algo;
//独立做
import java.util.Arrays;

public class SpecialArrayWithX {
    int getSpeicalX(int[] array) {
        Arrays.sort(array);
        int left = 0, right = array.length - 1;
        int n = array.length;
        while (left < right) {

            int mid = (left + right) / 2;
            if (n - mid == array[mid]){
                return array[mid];
            }
            else if (n - mid < array[mid]){
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return 0;
    }

    public static int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int x = 1; x <= n; ++x) {
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (nums[mid] >= x) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            int cnt = n - left;
            if (cnt == x) {
                return x;
            }
        }
        return -1;
    }
    // 先确定解空间，外层循环一定是解空间
    public static void main(String[] args) {
        int[] array = new int[]{0,4,5,6,7};
        System.out.println(specialArray(array));
    }
}
