package com.vinson.base;


public class StaticOrder {
	public static StaticOrder singleton = new StaticOrder();
	public static int a;
	public static int b = 0;

	private StaticOrder() {
		super();
		a++;
		b++;
	}

	public static StaticOrder GetInstence() {
		return singleton;
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		StaticOrder mysingleton = StaticOrder.GetInstence();
		System.out.println(mysingleton.a);
		System.out.println(mysingleton.b);
		Class<String> s = String.class;
	       System.out.println(s.getMethod("copyValueOf", new Class[]{char[].class, int.class,int.class}));
	}
}