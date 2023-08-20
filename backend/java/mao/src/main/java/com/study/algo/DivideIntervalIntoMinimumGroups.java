package com.study.algo;

import java.util.Arrays;
import java.util.PriorityQueue;
//求将其分成最少多少组，使得每组中的区间都不相交
public class DivideIntervalIntoMinimumGroups {
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        /*
        PQ 排序作用在1下标，Array sort作用在0下标
         */
        for (int[] e : intervals) {
            if (!q.isEmpty() && q.peek() < e[0]) {
                q.poll();
            }
            q.offer(e[1]);
        }
        return q.size();
    }
}
