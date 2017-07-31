package com.vinson.pattern.dynamicproxy;

public class Test {

	public static void main(String[] args) {
		ProxyFactoryBean<Subject> proxy = new ProxyFactoryBean<Subject>(new RealSubject());
		Subject sub = proxy.getProxy();
		sub.doSomething();
		System.out.println(true & false);
	}
}
