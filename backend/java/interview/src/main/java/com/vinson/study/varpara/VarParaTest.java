package com.vinson.study.varpara;


public class VarParaTest {

	public static void main(String[] args) {
		test();
		test("");
	}
	
	private static void test(String... paras) {
		System.out.println("Paras length is: " + paras.length);
	}
}
