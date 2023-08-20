package com.study.algo;

import java.util.Arrays;
import java.util.PriorityQueue;
/*
特征 无序数组选k个 -》 PQ, 如果有两个无序数组合并到一个二维数组中，一遍遍历解决极值判断逻辑
sequence 问题也离不开遍历，先做顺序处理再遍历出最优子集
靠数组来保证的[1]上的数字已经确定，就是从堆中pop出最小的可能是新的乘机最大
两个变量最终结果的极值：先保证一个确定且最优，另一个根据策略或者遍历得到
 */
//https://leetcode.ca/2023-03-10-2542-Maximum-Subsequence-Score/
public class MaximumSubSequenceScore {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i] = new int[]{nums1[i], nums2[i]};
        }
         /*
        PQ 排序作用在0下标，Array sort作用在1下标
         */
        Arrays.sort(nums, (a, b) -> b[1] - a[1]);
        long ans = 0, s = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            s += nums[i][0];
            q.offer(nums[i][0]);
            if(q.size() == k){
                ans = Math.max(ans, s * nums[i][1]);
                s -= q.poll();
            }
        }
        return ans;
    }
}

/*
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
}*/
