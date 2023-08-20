package com.study.algo;

import java.util.*;

//https://leetcode.ca/2018-12-27-1123-Lowest-Common-Ancestor-of-Deepest-Leaves/
/*
需要根据左右两颗树的情况决定最终递归后返回值，让返回值携带信息回传，便于两边统计
 */
public class LCADeepestLeaves {
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
        TreeNode t10 = new TreeNode(10);
        t3.left = t5;
        t3.right = t1;
        t5.left = t6;
        t5.right = t2;
        t1.left = t0;
        t1.right = t8;
        t2.left = t7;
        t2.right = t4;
        t4.right = t9;
        t4.left = t10;
        Info info = dfs(t3);
        System.out.println(info.node.val + " " + info.depth);
    }

    private static Info dfs(TreeNode root) {
        if (root == null) {
            return new Info(null, 0);
        }
        Info l = dfs(root.left);
        Info r = dfs(root.right);
        int d1 = l.getDepth(), d2 = r.getDepth();
        if (d1 > d2) {
            return new Info(l.getNode(), d1 + 1); //getNode返回的是common
        } else if (d1 < d2) {
            return new Info(r.getNode(), d2 + 1);
        }
        return new Info(root, d1 + 1);
    }

    static class Info {
        TreeNode node;
        int depth;

        public Info(TreeNode node, int depth) {
            super();
            this.node = node;
            this.depth = depth;
        }

        public TreeNode getNode() {
            return node;
        }

        public void setNode(TreeNode node) {
            this.node = node;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        List<TreeNode> deepestLeaves = new ArrayList<TreeNode>();
        Map<TreeNode, TreeNode> childParentMap = new HashMap<TreeNode, TreeNode>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            deepestLeaves.clear();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                TreeNode left = node.left, right = node.right;
                if (left == null && right == null)
                    deepestLeaves.add(node);
                if (left != null) {
                    childParentMap.put(left, node);
                    queue.offer(left);
                }
                if (right != null) {
                    childParentMap.put(right, node);
                    queue.offer(right);
                }
            }
        }
        if (deepestLeaves.size() == 1)
            return deepestLeaves.get(0);
        Set<TreeNode> set = new HashSet<TreeNode>();
        for (TreeNode node : deepestLeaves) {
            TreeNode parent = childParentMap.get(node);
            set.add(parent);
        }
        while (set.size() > 1) {
            List<TreeNode> list = new ArrayList<TreeNode>(set);
            set.clear();
            for (TreeNode node : list) {
                TreeNode parent = childParentMap.get(node);
                set.add(parent);
            }
        }
        List<TreeNode> resultList = new ArrayList<TreeNode>(set);
        return resultList.get(0);
    }
}


