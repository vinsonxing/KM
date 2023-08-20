package com.study.algo;
//https://leetcode.ca/2016-09-23-298-Binary-Tree-Longest-Consecutive-Sequence/
public class LongestConcecutiveBT {
    //中序可以把信息尽量带参数列表
    private int ans;

    private void dfs(TreeNode root, TreeNode prev, int len) {
        if (root == null) {
            return;
        }
        len = prev != null && prev.val + 1 == root.val ? len + 1 : 1;
        ans = Math.max(ans, len);
        dfs(root.left, root, len);
        dfs(root.right, root, len);
    }
}
