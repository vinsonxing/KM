package com.vinson;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


public class BinaryTree {

	private Node root;

	private static class Node {
		Node left;
		Node right;
		int data;

		Node(int newData) {
			left = null;
			right = null;
			data = newData;
		}

	}

	/**
	 * 创建一个空的二叉树
	 */

	public BinaryTree() {
		root = null;
	}
	
	/**
	 * 递归的插入数值
	 * @param data	要插入的数值
	 */

	public void insert(int data) {
		root = insert(root, data);
	}
	
	/**
	 * 将数值插入到二叉树中，比当前结点小或等于当前结点的插在当前结点的左侧，比当前结点大的数插在当前结点的右侧，每次从根结点开始递归比较
	 * @param node	当前的结点，就是根结点，只是每次根结点的左右子孙更新
	 * @param data	要插入的数值
	 * @return	新排好的二叉树
	 */

	private Node insert(Node node, int data) {

		if (node == null) {

			node = new Node(data);

		} else {
			if (data <= node.data) {
				node.left = insert(node.left, data);
			} else {
				node.right = insert(node.right, data);
			}
		}
		return (node);
	}
	
	/**
	 * 将数值输入构建二叉树
	 * @param data	要输入的数值
	 */

	public void buildTree(int[] data) {

		for (int i = 0; i < data.length; i++) {

			insert(data[i]);

		}

	}
	
	/**
	 * 递归打印出二叉树
	 */

	public void printTree() {

		printTree(root);

		System.out.println();

	}
	
	public void printLayerTree() {
		Queue<Node> q = new ArrayBlockingQueue<Node>(10);
		q.add(root);
		Node n = q.poll();
		System.out.print(n.data + "  ");
		while(n != null) {
			
			if (n.left != null) {
				System.out.print(n.left.data + "  ");
				q.add(n.left);
			}
			
			if (n.right != null) {
				System.out.print(n.right.data + "  ");
				q.add(n.right);
			}
				
		    n = q.poll();
		}
		
	}
	
	
	/**
	 * 从根结点开始遍历，从树的最高层叶子结点开始输出，从左至右
	 * @param node	当前的结点
	 */

	private void printTree(Node node) {

		if (node == null)
			return;

		// left, node itself, right

		
		printTree(node.left);

		System.out.print(node.data + "  ");

		printTree(node.right);

	}

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		int[] data = {6,1,7,2,4,9,3};
		bt.buildTree(data);
		bt.printTree();
		System.out.println("\n");
		bt.printLayerTree();
	}
}
