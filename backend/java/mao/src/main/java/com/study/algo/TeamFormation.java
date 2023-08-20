package com.study.algo;

import java.util.PriorityQueue;

public class TeamFormation {
    static int select(int[] score, int size, int k) {
        PriorityQueue<Integer> leftPq = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> rightPq = new PriorityQueue<>((a, b) -> b - a);
        int ans = 0, leftIndex = 0, rightIndex = score.length - 1;
        for (int i = leftIndex; i < k; i++) {
            leftPq.offer(score[i]);
            leftIndex++;
        }
        for (int i = rightIndex, j = 0; j < k; j++, i--) {
            rightPq.offer(score[i]);
            rightIndex--;
        }
        while (size != 0) {
            int leftMax = leftPq.peek();
            int rightMax = rightPq.peek();
            if (leftMax >= rightMax) {
                ans += leftPq.poll();
                leftPq.offer(score[leftIndex++]);
                size--;
            } else {
                ans += rightPq.poll();
                rightPq.offer(score[rightIndex--]);
                size--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] score = {17, 12, 10, 2, 7, 2, 11, 20, 8};
        System.out.println(select(score, 3, 4));
    }
}
