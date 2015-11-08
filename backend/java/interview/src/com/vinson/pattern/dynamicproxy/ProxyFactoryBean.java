package com.vinson.pattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactoryBean<T> implements InvocationHandler{
	
	private Object target;

	public ProxyFactoryBean(Object t) {
		// TODO Auto-generated constructor stub
		target = t;
	}
	
	@SuppressWarnings({ "unchecked"})
	public T getProxy() {
		return (T)Proxy.newProxyInstance(Subject.class.getClassLoader(), 
			     new Class[]{Subject.class}, 
			     this);

	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("Before do something");
		method.invoke(target, args);
		System.out.println("After do something");
		return null;
	}
}
