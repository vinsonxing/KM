package com.study.algo;

//https://www.bilibili.com/video/BV1SL411B71a?p=51&spm_id_from=pageDriver&vd_source=d64b61673a1ff62f40f5f6a1f7f407bf
/*
和MakeASortedMinimumSwaps完全套路一样
如果数组操作以后是每个位置可能一种情况，最后看各种情况的最优解，则是每个位置子数组问题
如果数组操作的目标是全局达到一个状态，状态特征确定，则考虑两状态递归
本质：
第一种情况你每个位置的结果都有可能是最优解，到i位置不用去考虑i+1
第二种情况（本题）到一个位置都不能决定最终结果，需要继续递归下去最终返回，也就是走完全局
 */
public class RemoveNumbersNotIncreasingAll {
    public static int getRemoval(int[] arr, int index, int len, int k) {
        if (len == k) {
            return Integer.MAX_VALUE;
        }
        if (index == arr.length) {
            return 0;
        }
        int curr = arr[index];
        if (len > curr || len + 1 < curr) {
            return getRemoval(arr, index + 1, len, k);
        }
        //stay
        int p1 = getRemoval(arr, index + 1, len + 1, k);
        //remove
        int p2 = Integer.MAX_VALUE;
        int next2 = getRemoval(arr, index + 1, len, k);
        if (next2 != Integer.MAX_VALUE) {
            p2 = next2 + 1;
        }
//        if (p1 == Integer.MAX_VALUE || p2 == Integer.MAX_VALUE) {
//            System.out.println("here..");
//        }
        return Math.min(p1, p2);
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 3, 4, 5};
        System.out.println(getRemoval(arr, 0, 0, 5));
    }
}
