package com.study.algo;
// 关键是理解dp的含义，dp蕴含最优，是扫描n长度得到的最优长度，不一定是以n结尾，
// 之所以i可以通过i-1得到是因为i-1代表扫描过i-1得到的最优当然应该以此作为依据去判断i长度，而不应该以i-1去判断
// 如果问题是最优，一般可以通过一个dp或max min 有限dp（n长度）得到，而如果去得到排列组合的可能性（种类），往往要求若干dp和（以不同可能性最为区分）
public class WiggleSubsequence {
    //https://leetcode.ca/all/376.html
    static int getLongestWiggleSeq(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][1] = dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                dp[i][0] = dp[i - 1][1] + 1;
                //如果是降序，则升序最长序列只能从前面元素继承下来而此次不能变更
                dp[i][1] = dp[i - 1][1];
            } else {
                dp[i][1] = dp[i - 1][0] + 1;
                dp[i][0] = dp[i - 1][0];
            }
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 8, 7, 9, 100};
//        int[] nums = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        System.out.println(getLongestWiggleSeq(nums));
    }
}
