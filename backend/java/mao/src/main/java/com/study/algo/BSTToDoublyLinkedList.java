package com.study.algo;
//中序prev 可以递归传，先序需要全局状态保存
public class BSTToDoublyLinkedList {
    static TreeNode prev;
    public static void inorderTraversal1(TreeNode root) {
        if(root == null) {
            return;
        }
        inorderTraversal1(root.left);
        if(prev != null) {
            prev.right = root;
        }
        root.left = prev;
        prev = root;
        inorderTraversal1(root.right);
    }




//    public static void inorderTraversal(TreeNode root, TreeNode prev) {
//        if(root == null) {
//            return;
//        }
//        inorderTraversal(root.left, prev);
//        if(prev != null) {
//            prev.right = root;
//        }
//        root.left = prev;
////        prev = root;
//        inorderTraversal(root.right, root);
//    }

    public static TreeNode treeToDoublyList(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode head = root, tail = root;
        while(head.left != null) {
            head = head.left;
        }
        while(tail.right != null){
            tail = tail.right;
        }

//        inorderTraversal(root, null);
        inorderTraversal1(root);
        head.left = tail;
        tail.right = head;
        return head;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t4.left = t2;
        t4.right = t5;
        t2.left = t1;
        t2.right = t3;
        TreeNode head = treeToDoublyList(t4);
        System.out.println(head.val);
    }
}
