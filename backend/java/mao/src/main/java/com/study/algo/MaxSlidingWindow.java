package com.study.algo;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//https://leetcode.ca/all/239.html
public class MaxSlidingWindow {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> arrayDeque = new ArrayDeque();
        int index = 0;
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (!arrayDeque.isEmpty() && arrayDeque.peek() < i - k + 1) {
                arrayDeque.poll();
            }
            while (!arrayDeque.isEmpty() && nums[i] > arrayDeque.peek()) {
                arrayDeque.poll();
            }
            arrayDeque.offer(i);
            if (i >= k - 1) {
                result[index++] = nums[arrayDeque.peek()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(maxSlidingWindow(input, 3)));
    }
}
