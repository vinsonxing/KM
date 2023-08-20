package com.study.algo;

//https://github.com/algorithmzuo/weekly-problems/blob/main/src/class_2022_05_3_week/Code01_MaxNumberUnderLimit.java
public class MaxNumberUnderLimit {
    public static int process2(int[] arr, int limit, int offset) {
        // 之前的数字和limit完全一样，且limit所有数字都一样
        if (offset == 0) {
            return limit;
        }
        // 当前的数字
        // 68886
        // 10000
        // 6
        int cur = (limit / offset) % 10;
        // 6 arr中 <=6 最近的！
        int near = near(arr, cur);
        if (near == -1) {
            return -1;
        } else if (arr[near] == cur) { // 找出来的数字，真的和当前数字cur一样!
            int ans = process2(arr, limit, offset / 10);
            if (ans != -1) {
                return ans;
            } else if (near > 0) { // 虽然后续没成功，但是我自己还能下降！还能选更小的数字
                near--;
                return (limit / (offset * 10)) * offset * 10 + (arr[near] * offset) + rest(arr, offset / 10);
            } else { // 后续没成功，我自己也不能再下降了！宣告失败，往上返回！
                return -1;
            }
        } else { // arr[near] < cur
            return (limit / (offset * 10)) * offset * 10 + (arr[near] * offset) + rest(arr, offset / 10);
        }
    }
    public static int near(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int near = -1;
        while (l < r) {
            m = (l + r) / 2;
            if (arr[m] < num) {
                near = m;
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    // 比如offset = 100
    // 一共3位数
    // 那么就把arr中最大的数字x，拼成xxx，返回
    // 比如offset = 10000
    // 一共5位数
    // 那么就把arr中最大的数字x，拼成xxxxx，返回
    public static int rest(int[] arr, int offset) {
        int rest = 0;
        while (offset > 0) {
            rest += arr[arr.length - 1] * offset;
            offset /= 10;
        }
        return rest;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 5, 8};
        System.out.println(near(arr, 7));
    }
}
