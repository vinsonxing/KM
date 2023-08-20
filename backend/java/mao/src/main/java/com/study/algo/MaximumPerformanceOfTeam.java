package com.study.algo;

import java.util.Arrays;
import java.util.PriorityQueue;

//https://leetcode.ca/2019-09-13-1383-Maximum-Performance-of-a-Team/
public class MaximumPerformanceOfTeam {
    int getMaxPerformance(int[] speed, int[] eff, int k) {
        int n = speed.length;
        int[][] employees = new int[n][2];
        for (int i = 0; i < n; i++) {
            employees[i] = new int[]{speed[i], eff[i]};
        }
        Arrays.sort(employees, (a, b) -> b[1] - a[1]);
        PriorityQueue<Integer> p = new PriorityQueue<>();
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += employees[i][0];
            p.offer(employees[i][0]);
            if (p.size() == k) {
                ans = Math.max(ans, sum * employees[i][1]);
                sum -= p.poll();
            }
        }
        return ans;
    }
}
