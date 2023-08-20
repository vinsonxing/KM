package com.study.algo;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber {
    public static int rob(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    public static int rob11(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }

    public static int rob2(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        int p1 = Math.max(dp[n - 1][0], dp[n - 1][1]);
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        int p2 = dp[n - 1][0];
        return Math.max(p1, p2);
    }

    public static int rob22(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        int p1 = dp[n];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        int p2 = dp[n - 1];
        return Math.max(p1, p2);
    }
    //////////////////////////////////
    private static Map<TreeNode, Integer> memo;

    public static int rob(TreeNode root) {
        memo = new HashMap<>();
        return dfs(root);
    }

    private static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {//回溯
            return memo.get(root);
        }
        int a = dfs(root.left) + dfs(root.right);
        int b = root.val;
        if (root.left != null) {
            b += dfs(root.left.left) + dfs(root.left.right);
        }
        if (root.right != null) {
            b += dfs(root.right.left) + dfs(root.right.right);
        }
        int res = Math.max(a, b);
        memo.put(root, res);
        return res;
    }

    public static void main(String[] args) {
//        int[] nums = {2, 7, 9, 3, 1};
//        System.out.println(rob(nums));
//        System.out.println(rob11(nums));
//
//        int[] nums1 = {2, 3, 2};
//        System.out.println(rob22(nums1));

        TreeNode t1 = new TreeNode(4);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(3);
        t1.left = t2;
        t2.left = t3;
        t3.left = t4;
        System.out.println(rob(t1));
    }
}
