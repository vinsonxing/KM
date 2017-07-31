package com.vinson.util.list;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeTest {

	public static void main(String[] args) {
//
//		test0();
//		test1();
		test2(true);
		test2(false);
	}
	
	private static void test0() {
		// test the bit calculation
		ArrayDeque<String> ad = new ArrayDeque<>();
		ad.addFirst("first"); // add the element to the last of embedded array.
								
		ad.addFirst("second");
		ad.addFirst("third");
		ad.addFirst("forth");
	}
	
	private static void test1() {
		Deque<String> stack = new ArrayDeque<String>();
		Deque<String> queue = new ArrayDeque<String>();

		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");

		while (!stack.isEmpty())
			System.out.print(stack.pop() + " ");  // stack print: D B C A

		queue.add("A");
		queue.add("B");
		queue.add("C");
		queue.add("D");
		System.out.println("");
		while (!queue.isEmpty())
			System.out.print(queue.remove() + " "); // queue print: A B C D
	}
	
	private static void test2(boolean isPop) {
		
		Deque<String> queue = new ArrayDeque<String>(); // the default size is 16.

		queue.add("A");
		queue.add("B");
		queue.add("C");
		queue.add("D");
		queue.add("A");
		queue.add("B");
		queue.add("C");
		queue.add("D");
		
		queue.push("E");
		queue.push("F");
		queue.push("G");
		queue.push("H");
		queue.push("E");
		queue.push("F");
		queue.push("G");
		queue.push("H"); // if uncomment this line, the deque size will be extended to 32
		if (isPop) {
			while (!queue.isEmpty())
				System.out.print(queue.pop() + " "); // [H, G, F ... F, E, A, B, C ....C, D, null,null ... , null]  pop the stack from first£¬ if the stack is done, continue pop the queue added by add().
		}
		
		System.out.println("");
		while (!queue.isEmpty())
			System.out.print(queue.remove() + " "); // [H, G, F ... F, E, A, B, C ....C, D, null,null ... , null]  remove also from first.
	}
}
