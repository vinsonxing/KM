package com.study.algo;

import java.util.ArrayList;
import java.util.List;
//如果不是BST，则需要优先队列进行排序，出队列的是peek出的最大的，而如果本题是BST，按先续可以保证进入数组的元素的大小关系
public class ClosestValueInBSTII {
    List<Integer> result = new ArrayList<>();
    double target;
    int k;
    public List<Integer> closestKValues(TreeNode root, double target, int k){
        this.target = target;
        this.k = k;
        dfs(root);
        return result;
    }
    void dfs(TreeNode root){
        if(root == null) {
            return;
        }
        dfs(root.left);
        if(result.size() < k){
            result.add(root.val);
        } else {
            if(Math.abs(root.val - target) < Math.abs(result.get(0) - target)) {
                result.remove(0);
                result.add(root.val);
            }
        }
        dfs(root.right); // 如果root 不符合要求，right也不会符合要求
    }
    public static void main(String[] args) {
        ClosestValueInBSTII solution = new ClosestValueInBSTII();
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        t5.left = t3;
        t5.right = t7;
        t3.left = t2;
        t3.right = t4;
        t7.left = t6;
        t7.right = t8;
        System.out.println(solution.closestKValues(t5, 3.6, 2));
    }
}
