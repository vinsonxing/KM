package com.study.algo;

//https://leetcode.ca/2021-07-30-1909-Remove-One-Element-to-Make-the-Array-Strictly-Increasing/
//不是动态规划问题
public class RemoveOneElementToMakeTheArrayIncreasing {
    static boolean isPossible(int[] arr) {
        int n = arr.length;
        boolean[][] dp = new boolean[n][3];
        dp[0][0] = true; //不删
        dp[0][1] = true; //删自己
        dp[0][2] = false; //删前面的
        for (int i = 1; i < n; i++) {

            if (arr[i] > arr[i - 1]) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][0];
                dp[i][2] = i == 1 ? true : (arr[i] > arr[i - 2]) && dp[i - 1][1] || dp[i - 1][2];
            } else {
                dp[i][0] = false;
                dp[i][1] = dp[i - 1][0];
                dp[i][2] = i == 1 ? false : (arr[i] > arr[i - 2]) && dp[i - 1][1];
            }
        }

        return dp[n - 1][1] | dp[n - 1][2];
    }

    static boolean isPossible1(int[] arr) {
        int length = arr.length;
        int count = 0;
        int last = arr[0];
        for (int i = 1; i < length; i++) {
            if (arr[i] <= last) {
                count++;
                if (count == 2) {
                    return false;
                }
                if (i > 1 && arr[i] < arr[i - 2]) {
                    last = arr[i - 1];
                } else {
                    last = arr[i];
                }
            } else {
                last = arr[i];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //int[] arr = {2, 3, 1, 2};
        int[] arr = {1, 2, 10, 5, 7, 8};
//        int[] arr = {1, 1, 1};
        //int[] arr = {1, 2, 3};
        System.out.println(isPossible(arr));
    }

}
