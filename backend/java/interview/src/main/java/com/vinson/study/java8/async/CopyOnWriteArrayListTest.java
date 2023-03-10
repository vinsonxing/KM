package com.vinson.study.java8.async;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {

	public static void main(String[] args) {
		testArrayList(); // Exception in thread "main" java.util.ConcurrentModificationException
//		testCopyOnWriteArrayList(); // Can run, and only can get new added value in new iterator
	}

	private static void testArrayList() {

		ArrayList<String> list = new ArrayList<>();

		// Initial Iterator
		Iterator itr = list.iterator();
		list.add("GfG");
		System.out.println("List contains: ");
		while (itr.hasNext())
			System.out.println(itr.next());

		// iterator after adding an element
		itr = list.iterator();
		System.out.println("List contains:");
		while (itr.hasNext())
			System.out.println(itr.next());
	}

	private static void testCopyOnWriteArrayList() {

		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

		// Initial Iterator
		Iterator itr = list.iterator();
		list.add("GfG");
		System.out.println("List contains: ");
		while (itr.hasNext())
			System.out.println(itr.next());

		// iterator after adding an element
		itr = list.iterator();
		System.out.println("List contains:");
		while (itr.hasNext())
			System.out.println(itr.next());
	}
}
