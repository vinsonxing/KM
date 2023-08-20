package com.study.algo;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Stack;

public class BSTIterator implements Iterator<TreeNode> {
    /*ArrayDeque<TreeNode> stack = new ArrayDeque<>();

    private void push(TreeNode root){
        if(root == null) {
            return;
        }
        push(root.left);
        stack.push(root);
        push(root.right);
    }
    public BSTIterator(TreeNode root){
        push(root);
    }
    public TreeNode next(){
        return stack.pollLast();
    }*/
    public boolean hasNext(){
        return !stack.isEmpty();
    }
    Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();

        // all the way to leftmost leaf
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }



    public TreeNode next() {
        TreeNode minNode = stack.pop();

        TreeNode current = minNode;
        // update stack, possible next time min is from its right-then-left branch
        if (current.right != null) {
            current = current.right;

            // same logic as in constructor
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }
        return minNode;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        t6.left = t4;
        t6.right = t7;
        t4.left = t1;
        t4.right = t5;
        t1.right = t3;
        t3.left = t2;
        BSTIterator iterator = new BSTIterator(t6);
        while(iterator.hasNext()){
            System.out.println(iterator.next().val);
        }
    }
}
