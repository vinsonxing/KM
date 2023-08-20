package com.study.algo;
//https://leetcode.ca/2016-07-17-230-Kth-Smallest-Element-in-a-BST/
import java.util.Comparator;
import java.util.PriorityQueue;
//What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
//第K小的想优先队列，和矩阵找第K小异曲同工
public class KthSmallestInBST {
    PriorityQueue<Integer> heap = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    private void dfs(TreeNode root, int k){
        if (root == null){
            return;
        }
        dfs(root.left, k);
        if(heap.size() < k) {
            heap.offer(root.val);
        } else {
            if (root.val < heap.peek()){
                heap.poll();
                heap.offer(root.val);
            }
        }
        dfs(root.right, k);
    }
    int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return heap.peek();
    }
}

/*class Solution_followUp {
    public int kthSmallest(TreeNode root, int k) {
        MyTreeNode node = build(root);
        return dfs(node, k);
    }

    class MyTreeNode {
        int val;
        int count; // key point to add up and find k-th element
        MyTreeNode left;
        MyTreeNode right;
        MyTreeNode(int x) {
            this.val = x;
            this.count = 1;
        }
    };

    MyTreeNode build(TreeNode root) {
        if (root == null) return null;
        MyTreeNode node = new MyTreeNode(root.val);
        node.left = build(root.left);
        node.right = build(root.right);
        if (node.left != null) node.count += node.left.count;
        if (node.right != null) node.count += node.right.count;
        return node;
    }

    int dfs(MyTreeNode node, int k) {
        if (node.left != null) {
            int cnt = node.left.count;
            if (k <= cnt) {
                return dfs(node.left, k);
            } else if (k > cnt + 1) {
                return dfs(node.right, k - 1 - cnt); // -1 is to exclude current root
            } else { // k == cnt + 1
                return node.val;
            }
        } else {
            if (k == 1) return node.val;
            return dfs(node.right, k - 1);
        }
    }
}*/
