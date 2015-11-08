package com.vinson.util.list;

import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {

	public static void main(String[] args) {
		LinkedList<String> l = new LinkedList<>();
		l.addFirst("2");
		l.addFirst("1");
		System.out.println(l);
		String[] ss = {"1", "2"};
		Object[] o = ss; // {"1", "2"} error
		String[] s = (String[])o;
		
		for (String str :s) {
			System.out.println(str);
			
		}
	}
}
