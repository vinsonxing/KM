package com.study.algo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
/*
复杂动态方程，推导（等号右边）依赖不确定因子，可以依赖优先队列或者双端队列
 */
//https://leetcode.ca/2020-07-13-1687-Delivering-Boxes-from-Storage-to-Ports/
public class DelieverBoxFromStorgeToPort {
    public static int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int length = boxes.length;
        int[] ports = new int[length + 1];
        int[] weights = new int[length + 1];
        int[] differences = new int[length + 1];
        long[] prefixWeights = new long[length + 1];
        for (int i = 1; i <= length; i++) {
            ports[i] = boxes[i - 1][0];
            weights[i] = boxes[i - 1][1];
            if (i > 1)
                differences[i] = differences[i - 1] + (ports[i - 1] != ports[i] ? 1 : 0);
            prefixWeights[i] = prefixWeights[i - 1] + weights[i];
        }
        Deque<Integer> deque = new LinkedList<Integer>();
        deque.offerLast(0);
        int[] dp = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            //从前面拿箱子多
            while (!deque.isEmpty() && (i - deque.peekFirst() > maxBoxes || prefixWeights[i] - prefixWeights[deque.peekFirst()] > maxWeight))
                deque.pollFirst();
            dp[i] = dp[deque.peekFirst()]  + differences[i] - differences[deque.peekFirst() + 1] + 2;
            if (i != length) {
                while (!deque.isEmpty() && dp[i] - differences[i+1] < dp[deque.peekLast()] - differences[deque.peekFirst() + 1])
                    deque.pollLast();
                deque.offerLast(i);
        }
        }
        return dp[length];
    }

    public static void main(String[] args) {
        int[][] boxes = {{1,4},{1,2},{2,1},{2,1},{3,2},{3,4}};
        System.out.println(boxDelivering(boxes, 3,6,7));
    }
}
