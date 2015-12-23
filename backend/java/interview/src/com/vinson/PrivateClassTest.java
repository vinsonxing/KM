package com.vinson;

public class PrivateClassTest {

	private int i;
	
	public void test() {
		new innerClass().privateMethod();
	}
	
	private class innerClass {
		public innerClass() {
			// TODO Auto-generated constructor stub
		}
		
		private void privateMethod() {
			i = i + 1;
			System.out.println("--->private Method");
		}
	}
	
	public static void main(String[] args) {
		PrivateClassTest pct = new PrivateClassTest();
		pct.test();
	}
}
