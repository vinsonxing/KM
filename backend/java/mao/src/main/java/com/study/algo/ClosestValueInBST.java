package com.study.algo;

public class ClosestValueInBST {
    //below is BST
    private static TreeNode closest1(TreeNode root, double target){
        TreeNode child = root.val > target ? root.left : root.right;
        if(child == null){
            return root;
        }
        TreeNode childCloset = closest1(child, target);
        return Math.abs(root.val - target) < Math.abs(childCloset.val - target) ? root : childCloset;
    }
    //below is BT
    private static TreeNode closest(TreeNode root, double target) {
        if (root == null ) {
            return null;
        }
        if (root.val == target){
            return root;
        }
        double closest = Math.abs(root.val - target);
        TreeNode left = closest(root.left, target);
        TreeNode right = closest(root.right, target);
        if (left == null && right == null) {
            return root;
        } else if (left == null) {
            return Math.abs(right.val - target) <= closest ? right : root;
        } else if (right == null) {
            return Math.abs(left.val - target) <= closest ? left : root;
        } else{
            double ld = Math.abs(left.val - target), rd = Math.abs(right.val - target);
            if (ld < rd && ld < closest) {
                return left;
            } else if (rd < ld && rd < closest) {
                return right;
            } else {
                return root;
            }
        }
    }

//    private static TreeNode closestII(TreeNode root, double target) {
//
//    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t4.left = t2;
        t4.right = t5;
        t2.left = t1;
        t2.right= t3;
        System.out.println(closest1(t4, 5.01).val);
    }
}

