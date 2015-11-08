package com.vinson.pattern.dynamicproxy;

public class RealSubject implements Subject{
	@Override
	public void doSomething() {
		
		System.out.println("Do something here !!");
		
	}
}
