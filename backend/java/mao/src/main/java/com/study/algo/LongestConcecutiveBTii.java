package com.study.algo;
//https://leetcode.ca/2017-06-01-549-Binary-Tree-Longest-Consecutive-Sequence-II/
public class LongestConcecutiveBTii {
    int helper(TreeNode node, int diff) {
        if (node == null) {
            return 0;
        }
        int left = 0, right = 0;
        if (node.left != null && node.val - node.left.val == diff) {
            left = 1 + helper(node.left, diff);
        }
        if (node.right != null && node.val - node.right.val == diff) {
            right = 1 + helper(node.right, diff);
        }
        return Math.max(left, right);
    }

    public int longestConcecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = helper(root, 1) + helper(root, -1) + 1;
        return Math.max(res, Math.max(longestConcecutive(root.left), longestConcecutive(root.right)));
    }
}
