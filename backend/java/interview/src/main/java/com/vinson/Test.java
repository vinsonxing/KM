package com.vinson;

public class Test {
	public static void main(String[] args) {
		System.out.println(3>>1);
		Test t = new Test();
		Test.Inner ti = t.new Inner();
	}


	private class Inner {

	}
}
