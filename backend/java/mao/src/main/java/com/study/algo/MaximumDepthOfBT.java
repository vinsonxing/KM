package com.study.algo;

public class MaximumDepthOfBT {
    static int getMaximumDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftDepth = getMaximumDepth(root.left);
        int rightDepth = getMaximumDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
    static int getMinimumDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftDepth = getMinimumDepth(root.left);
        int rightDepth = getMinimumDepth(root.right);
        if(root.left == null && root.right != null) {
            return rightDepth + 1;
        } else if (root.right == null && root.left != null) {
            return leftDepth + 1;
        } else  {
            return Math.min(leftDepth, rightDepth) + 1;
        }
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
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t5.left = t6;
        t5.right = t8;
        t6.right = t7;
//        t3.right = t7;
        System.out.println(getMaximumDepth(t1));
        System.out.println(getMinimumDepth(t1));
    }
}
