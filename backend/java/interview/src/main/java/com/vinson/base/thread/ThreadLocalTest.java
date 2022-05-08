package com.vinson.base.thread;

public class ThreadLocalTest {

	private static final ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> {
        return "Initial value";
    });
	
	public static void main(String[] args) throws Exception {
		 System.out.println(threadLocal.get());
		
	}
}
