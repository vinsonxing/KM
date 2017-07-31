package com.vinson.base;

import java.util.Comparator;


public class java8 {

	public static void main(String[] args) {

		Comparator<String> c = (a, b) -> Integer.compare(a.length(),
                b.length());
		
		Comparator<String> cc = new Comparator<String>() {
			
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.length(),
		                o2.length());
			}
		};
		
		cc.equals(new Object());
	}
}
