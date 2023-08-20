package com.study.algo;
//https://leetcode.ca/2017-09-19-659-Split-Array-into-Consecutive-Subsequences/
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
//独立做
public class SplitArrayIntoSubseq {
    public static boolean isPossible(int[] nums) {      
        Map<Integer, ArrayDeque<Integer>> d = new HashMap<>();
        for (int v : nums) {
            if (d.containsKey(v - 1)) {
                ArrayDeque<Integer> q = d.get(v - 1);
                //出+进
                d.computeIfAbsent(v, k -> new ArrayDeque<>()).offer(q.poll() + 1);
                if (q.isEmpty()) {
                    d.remove(v - 1);
                }
            } else {
                d.computeIfAbsent(v, k -> new ArrayDeque<>()).offer(1);
            }
        }
        for (ArrayDeque<Integer> v : d.values()) {
            if (v.peek() < 3) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,4,2,3,5,6};
        System.out.println(isPossible(nums));
//        PriorityQueue<Integer> test = new PriorityQueue<>();
//        test.offer(1);
//        test.offer(2);
//        test.offer(3);
//        System.out.println(test.poll());
//        System.out.println(test.poll());
//        System.out.println(test.poll());
    }
}
