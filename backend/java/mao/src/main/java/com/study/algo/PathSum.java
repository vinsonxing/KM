package com.study.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathSum {
    static List<List<Integer>> ans = new ArrayList<>();
    static int count = 0;

    static void findPaths(TreeNode root, int sum, int currsum, List<Integer> path) {
        if (root.left == null && root.right == null) {
            if (currsum == sum) {
                ans.add(path);
            }
            return;
        }
        if (root.left != null) {
            List<Integer> leftPath = new ArrayList<>();
            leftPath.addAll(path);
            leftPath.add(root.left.val);
            findPaths(root.left, sum, currsum + root.left.val, leftPath);
        }
        if (root.right != null) {
            List<Integer> rightPath = new ArrayList<>();
            rightPath.addAll(path);
            rightPath.add(root.right.val);
            findPaths(root.right, sum, currsum + root.right.val, rightPath);
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(4);
        TreeNode t3 = new TreeNode(8);
        TreeNode t4 = new TreeNode(11);
        TreeNode t5 = new TreeNode(13);
        TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(2);
        TreeNode t9 = new TreeNode(5);
        TreeNode t10 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t3.left = t5;
        t3.right = t6;
        t4.left = t7;
        t4.right = t8;
        t6.left = t9;
        t6.right = t10;
        List<Integer> path = new ArrayList<>();
        path.add(t1.val);
        findPaths(t1, 22, t1.val, path);
       // Integer[][] array = ans.stream().toArray(Integer[][]::new);
        List<Integer[]> ans1 = new ArrayList<>();
        for (int i = 0; i < ans.size(); i++) {
            Integer[] each = ans.get(i).stream().toArray(Integer[]::new);
            ans1.add(each);
        }
        Integer[][] array = ans1.stream().toArray(Integer[][]::new);
    }

}
