package com.vinson.base.classloader;


public class ClassLoaderTest {

	public static void main(String[] args) throws Exception {
		// System.out.println(threadLocal.get());
		
		// Case 1:
		// won't run static block
		Class<StaticClass> clazz1 = StaticClass.class;
		clazz1.newInstance(); // if run this, static block will be ran

		// Case 2:
		// won't run static block
		Class<?> clazz2 = ClassLoaderTest.class.getClassLoader().loadClass("com.vinson.base.classloader.StaticClass");
				
		// Case 3:
		// won't run static block
		Class<?> clazz3 = Class.forName("com.vinson.base.classloader.StaticClass");
	}
	
}
