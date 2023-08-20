package com.study.algo;

import java.util.*;
//Each node in the tree has unique values 0 <= node.val <= 500. ->set
//https://leetcode.ca/all/863.html
public class AllNodesDistanceKInBT {
    private static Map<TreeNode, TreeNode> p;
    private static Set<Integer> vis;
    private static List<Integer> ans;

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        p = new HashMap<>();
        vis = new HashSet<>();
        ans = new ArrayList<>();
        parents(root, null);
        dfs(target, k);
        return ans;
    }

    private static void parents(TreeNode root, TreeNode prev) {
        if (root == null) {
            return;
        }
        p.put(root, prev);
        parents(root.left, root);
        parents(root.right, root);
    }
    private static void dfs(TreeNode root, int k) {
        if (root == null || vis.contains(root.val)) {
            return;
        }
        vis.add(root.val);
        if (k == 0) {
            ans.add(root.val);
            return;
        }
        dfs(root.left, k - 1);
        dfs(root.right, k - 1);
        dfs(p.get(root), k - 1);
    }

    public static void main(String[] args) {
        TreeNode t0 = new TreeNode(0);
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);
        t3.left = t5;
        t3.right = t1;
        t5.left = t6;
        t5.right = t2;
        t2.left = t7;
        t2.right = t4;
        t1.left = t0;
        t1.right = t8;
        System.out.println(distanceK(t3, t2,1));
    }
}
