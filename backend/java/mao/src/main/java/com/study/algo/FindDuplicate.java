package com.study.algo;

//https://www.bilibili.com/video/BV1SL411B71a?p=62&spm_id_from=pageDriver&vd_source=d64b61673a1ff62f40f5f6a1f7f407bf
//有序（二分） + 无序（快慢双指针）
public class FindDuplicate {
    public static int findDuplicate(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (m - 1 >= 0 && arr[m - 1] == arr[m] || m + 1 < arr.length && arr[m] == arr[m + 1]) {
                ans = arr[m];
                break;
            }
            if (m - l == arr[m] - arr[l]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }

    public static int findDuplicate1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int slow = arr[0];
        int fast = arr[arr[0]];
        while (slow != fast) {
            slow = arr[slow];
            fast = arr[arr[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = arr[slow];
            fast = arr[arr[fast]];
        }
        return slow;
    }
}
