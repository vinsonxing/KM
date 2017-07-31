package com.vinson.algorithm;


public class FeiBoNaQie {

	
	public static int feibonaqie(int n) {
		if (n <= 2) return 1;
		
		int a = 1;
		int b = 1;
		int t = -1;
		for (int i = 2; i < n; i ++) {
			t = b;
			b = a + b;
			a = t;
		}
		
		return b;
	}
	
	public static void main(String[] args) {
		System.out.println(feibonaqie(1));
		System.out.println(feibonaqie(2));
		System.out.println(feibonaqie(3));
		System.out.println(feibonaqie(4));
		System.out.println(feibonaqie(5));
		System.out.println(feibonaqie(6));
		System.out.println(feibonaqie(7));
		System.out.println(feibonaqie(8));
		System.out.println(feibonaqie(9));
		System.out.println(feibonaqie(10000));
	}
}
