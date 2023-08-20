package com.study.algo;
//https://leetcode.ca/all/897.html
public class IncreasingOrderST {

    private TreeNode prev = new TreeNode(0, null, null);



    private void dfs1(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs1(root.left);
        prev.right = root;
        root.left = null;
        prev = root;
        dfs1(root.right);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);
        t6.left = t2;
        t6.right = t7;
        t2.left = t1;
        t2.right = t4;
        t4.left = t3;
        t4.right = t5;
        t7.right = t9;
        t9.left = t8;
        IncreasingOrderST ist = new IncreasingOrderST();
        ist.dfs1(t6);
        System.out.println();
    }
}
