package com.study.algo;

import java.util.Arrays;
//todo
//没看懂
//https://leetcode.ca/2020-01-16-1508-Range-Sum-of-Sorted-Subarray-Sums/
public class RangeSumOfSortedSubarray {
    static int[] a = new int[]{1, 2, 3, 4};
    static int[] b = new int[a.length + 1];

    public static void main(String[] args) {

        Arrays.fill(b, 0);

        for (int i = 0; i < a.length; i++) {
            b[i + 1] = a[i] + b[i];
        }
        System.out.println(Arrays.toString(b));
//        System.out.println(Arrays.toString(c));
        int right = 7, left = 3;
        System.out.println(sumKSums(right) - sumKSums(left - 1));
        System.out.println(rangeSum(a, 4, 3, 7));
    }

    static int countSumUnder(int[] b, int score) { // Use sliding window to count the partial sums that are smaller or equal to `score`.
        int ans = 0, i = 0;
        for (int j = 0; j < b.length; ++j) {
            while (b[j] - b[i] > score) {
                ++i;
            }
            ans += j - i;
        }
        return ans;
    }

    static int sumKSums(int k) { // Use sliding window to get the sum of the k smallest items in all the subarray sums.
        int score = kthScore(k), ans = 0, count = 0;
        for (int i = 0; i < b.length - 1; i++) {
            int j = i + 1;
            while (j < b.length && b[j] - b[i] < score) {
                ans += b[j] - b[i];
                j++;
            }
            j--;
            count += j - i;
        }
        ans += (k - count) * score;
        return ans;
    }

    static int kthScore(int k) { // use binary search to find the kth smallest sum in all subarray sums.
        int L = 0, R = b[a.length];
        while (L < R) {
            int M = (L + R) / 2;
            if (countSumUnder(b, M) < k) {
                L = M + 1;
            } else {
                R = M;
            }
        }
        return L;
    }

    // 对数器
    public static int rangeSum(int[] nums, int n, int left, int right) {
        int[] arr = new int[n * (n + 1) / 2];
        for (int i = 0, k = 0; i < n; ++i) {
            int s = 0;
            for (int j = i; j < n; ++j) {
                s += nums[j];
                arr[k++] = s;
            }
        }
        Arrays.sort(arr);
        int ans = 0;
        final int mod = (int) 1e9 + 7;
        for (int i = left - 1; i < right; ++i) {
            ans = (ans + arr[i]) % mod;
        }
        return ans;
    }
}
