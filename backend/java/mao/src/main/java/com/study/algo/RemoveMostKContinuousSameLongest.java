package com.study.algo;
//https://www.bilibili.com/video/BV1SL411B71a?p=85
import java.util.HashMap;
import java.util.LinkedList;
//以当前字符为结尾字符的子数组问题,遍历全局求出最优
//特征：每个位置都有一个确定的状态
public class RemoveMostKContinuousSameLongest {
    //双端队列，数据准备的数组下标index map很重要，子数组问题要记得考虑
    public static int longest(int[] arr, int k) {
        HashMap<Integer, LinkedList<Integer>> valueIndexes = new HashMap<>();
        int ans = 1;
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            if(!valueIndexes.containsKey(value)) {
                valueIndexes.put(value, new LinkedList<>());
            }
            LinkedList<Integer> indexes = valueIndexes.get(value);
            while (!indexes.isEmpty() && i - indexes.peekFirst() - indexes.size() > k) {
                indexes.pollFirst();
            }
            indexes.addLast(i);
            ans = Math.max(ans, indexes.size());
        }
        return ans;
    }
}
