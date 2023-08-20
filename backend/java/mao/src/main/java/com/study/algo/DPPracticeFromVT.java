package com.study.algo;

import java.util.Arrays;
//同类型 https://leetcode.ca/2017-04-07-494-Target-Sum/
//https://www.bilibili.com/video/BV1SL411B71a?p=54&vd_source=d64b61673a1ff62f40f5f6a1f7f407bf
public class DPPracticeFromVT {
    /*
    如果条件是最值，那递归最后一个参数应该是走完当前元素剩余指标；
    如果条件是满足某种要求下最多元素个数，则递归最后一个参数应该是与前一个参数的连接条件,或者变换状态（连接问题记住pre）
     */
    //游戏最少方案，有失败情况-》额外条件
    public static int process(int[][] arr, int index, int restFunny, int restOffense) {
        /*
        前两个条件有先后顺序
         */
        if (restFunny <= 0 && restOffense <= 0) {
            return 0; //可以了
        }
        if (index == arr.length) { //求最小如果不可能则返回最大
            return Integer.MAX_VALUE;
        }
        int p1 = process(arr,  index + 1, restFunny, restOffense);
        int p2 = Integer.MAX_VALUE;
        int next = process(arr, index + 1, restFunny - arr[index][0], restOffense - arr[index][1]);
        if (next != Integer.MAX_VALUE) {
            p2 = 1 + next;
        }
        return Math.min(p1, p2);
    }

    //绳子最大价值，没有失败情况
    public static int maxValue(int[][] arr, int index, int restLen) {
        if (restLen <= 0 || index == arr.length) {
            return 0; //求最大不可能则返回0
        }
        int p1 = maxValue(arr, index + 1, restLen);
        int p2 = 0;
        if (arr[index][0] <= restLen) { //递归有条件
            int next = maxValue(arr, index, restLen - arr[index][0]); //index 没有加1
            p2 = arr[index][1] + next;
        }
        return Math.max(p1, p2);
    }

    //连接最多序列，没有失败情况
    public static int maxLen(int[][] arr, int index, int preIndex) {
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        if (index == arr.length) {
            return 0;   //求最大不可能则返回0
        }
        int p1 = maxLen(arr, index + 1, preIndex);
        int p2 = 0;
        if (arr[preIndex][1] == arr[index][0]) {
            p2 = 1 + maxLen(arr, index + 1, index);
        }
        return Math.max(p1, p2);
    }
}
