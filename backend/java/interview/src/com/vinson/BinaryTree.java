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
	 * ����һ���յĶ�����
	 */

	public BinaryTree() {
		root = null;
	}
	
	/**
	 * �ݹ�Ĳ�����ֵ
	 * @param data	Ҫ�������ֵ
	 */

	public void insert(int data) {
		root = insert(root, data);
	}
	
	/**
	 * ����ֵ���뵽�������У��ȵ�ǰ���С����ڵ�ǰ���Ĳ��ڵ�ǰ������࣬�ȵ�ǰ����������ڵ�ǰ�����Ҳ࣬ÿ�δӸ���㿪ʼ�ݹ�Ƚ�
	 * @param node	��ǰ�Ľ�㣬���Ǹ���㣬ֻ��ÿ�θ����������������
	 * @param data	Ҫ�������ֵ
	 * @return	���źõĶ�����
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
	 * ����ֵ���빹��������
	 * @param data	Ҫ�������ֵ
	 */

	public void buildTree(int[] data) {

		for (int i = 0; i < data.length; i++) {

			insert(data[i]);

		}

	}
	
	/**
	 * �ݹ��ӡ��������
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
	 * �Ӹ���㿪ʼ��������������߲�Ҷ�ӽ�㿪ʼ�������������
	 * @param node	��ǰ�Ľ��
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
