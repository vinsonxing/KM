package com.study.algo;
//https://leetcode.ca/all/103.html
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ZigZagOrderTraversal {
    static List<List<Integer>> traversal(TreeNode root) {
        List<List<Integer>> finalRes = new ArrayList<>();
        Deque<TreeNode> zigstack = new ArrayDeque<>();
        Deque<TreeNode> zagstack = new ArrayDeque<>();
        zigstack.push(root);

        while (!zigstack.isEmpty() || !zagstack.isEmpty()) {
            List<Integer> res = new ArrayList<>();
            while (!zigstack.isEmpty()) {

                TreeNode node = zigstack.pop();
                if (node.left != null) {
                    zagstack.push(node.left);
                }
                if (node.right != null) {
                    zagstack.push(node.right);
                }
                res.add(node.val);
            }
            if (res.size() > 0) {
                finalRes.add(res);
            }
            res = new ArrayList<>();
            while (!zagstack.isEmpty()) {
                TreeNode node = zagstack.pop();
                if (node.right != null) {
                    zigstack.push(node.right);
                }
                if (node.left != null) {
                    zigstack.push(node.left);
                }
                res.add(node.val);
            }
            if (res.size() > 0) {
                finalRes.add(res);
            }
        }
        return finalRes;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        System.out.println(traversal(t1));
    }

}
