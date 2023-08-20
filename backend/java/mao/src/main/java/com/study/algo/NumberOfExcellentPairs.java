package com.study.algo;
//滑动窗口思路
//https://www.bilibili.com/video/BV1fR4y1U7mk/?spm_id_from=333.999.0.0&vd_source=d64b61673a1ff62f40f5f6a1f7f407bf
import java.util.HashSet;
import java.util.Set;
//https://leetcode.ca/2022-09-03-2354-Number-of-Excellent-Pairs/
public class NumberOfExcellentPairs {
    public static void main(String[] args) {
        System.out.println(Integer.bitCount(15));
        long l = (long) Math.pow(10, 9);
        int size = Long.toBinaryString(l).length();
        System.out.println(size);
    }

    public long countExcellentPairs(int[] nums, int k) {
        long l = (long) Math.pow(10, 9);
        int size = Long.toBinaryString(l).length();
        long[] cnt = new long[size];
        long res = 0L;
        Set<Integer> set = new HashSet<>();
        for (int a : nums) {
            set.add(a);
        }
        for (int a : set) {
            cnt[Integer.bitCount(a)]++;
        }
        for (int i = 1; i < size; ++i) {
            for (int j = 1; j < size; ++j) {
                if (i + j >= k) {
                    res += cnt[i] * cnt[j];
                }
            }
        }
        return res;
    }
}
