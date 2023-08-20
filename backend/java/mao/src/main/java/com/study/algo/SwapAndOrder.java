package com.study.algo;

//https://www.bilibili.com/video/BV1SL411B71a?p=100&vd_source=d64b61673a1ff62f40f5f6a1f7f407bf
//#100
/*
B 数组可以交换，可以不交换，最终实现A数组递增有序
最终目的是一个递增状态，递增就需要前一个元素的状态记录
 */
public class SwapAndOrder {
    static boolean process(int[] A, int[] B) {
        int length = A.length;
        int[] C = new int[length];
        for (int i = 0; i < length; i++) {
            int min = Math.min(A[i], B[i]);
            int max = Math.max(A[i], B[i]);
            if (i == 0) {
                C[i] = min;
            } else {
                if (max < C[i - 1]) {
                    return false;
                } else if (max > C[i - 1] && min < C[i - 1]) {
                    C[i] = max;
                } else if (min > C[i - 1]) {
                    C[i] = min;
                }
            }
        }
        return true;
    }

    public static boolean process(int[] A, int[] B, int index, boolean swap) {
        if (index == A.length) {
            return true;
        }
        int pre = swap ? B[index - 1] : A[index - 1];
        boolean p1 = pre > A[index] ? false : process(A, B, index + 1, false);
        boolean p2 = pre > B[index] ? false : process(A, B, index + 1, true);
        return p1 || p2;
    }

    public static boolean process_dp(int[] A, int[] B) {
        int length = A.length;
        boolean[][] dp = new boolean[length][2];
        dp[0][0] = true;
        dp[0][1] = true;
        for (int i = 1; i < length; i++) {
            if (A[i] > A[i - 1] && A[i] > B[i - 1]) {
                dp[i][0] = dp[i - 1][0] || dp[i - 1][1];
            } else if (A[i] > A[i - 1] && A[i] < B[i - 1]) {
                dp[i][0] = dp[i - 1][0];
            } else if ((A[i] < A[i - 1] && A[i] > B[i - 1])) {
                dp[i][0] = dp[i-1][1];
            } else if ((A[i] < A[i - 1] && A[i] < B[i - 1])) {
                dp[i][0] = false;
            }
            if (B[i] > A[i - 1] && B[i] > B[i-1]) {
                dp[i][1] = dp[i - 1][0] || dp[i - 1][1];
            } else if (B[i] > A[i - 1] && B[i] < B[i - 1]) {
                dp[i][1] = dp[i - 1][0];
            } else if ((B[i] < A[i - 1] && B[i] > B[i - 1])) {
                dp[i][1] = dp[i-1][1];
            } else if ((B[i] < A[i - 1] && A[i] < B[i - 1])) {
                dp[i][1] = false;
            }
        }
        return dp[length - 1][0] | dp[length - 1][1];
    }


    public static void main(String[] args) {
        int[] A = new int[]{0, 6, 4, 5, 1};
        int[] B = new int[]{3, 2, 1, 3, 6};
        System.out.println(process(A, B));
        System.out.println(process_dp(A, B));
        System.out.println(process(A, B, 1, false) || process(A, B, 1, true));
    }
}

