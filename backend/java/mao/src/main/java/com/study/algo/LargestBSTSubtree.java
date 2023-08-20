package com.study.algo;
//让返回值携带信息回传，便于两边统计
public class LargestBSTSubtree {
    private int max = 0;
    class SubTreeData {
        int size = 0; // exclude root self
        int lower = Integer.MAX_VALUE;
        int upper = Integer.MIN_VALUE;
        boolean isBST = false;
    }

    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);

        return max;
    }
    private SubTreeData dfs(TreeNode root) {
        SubTreeData curr = new SubTreeData();
        if (root == null) {
            curr.isBST = true;
            return curr;
        }
        SubTreeData left = dfs(root.left);
        SubTreeData right = dfs(root.right);

        curr.lower = Math.min(root.val, Math.min(left.lower, right.lower));
        curr.upper = Math.max(root.val, Math.max(left.upper, right.upper));

        if (left.isBST && root.val > left.upper && right.isBST && root.val < right.lower) {
            curr.size = left.size + right.size + 1;
            curr.isBST = true;
            max = Math.max(max, curr.size);
        }

        return curr;
    }
}
