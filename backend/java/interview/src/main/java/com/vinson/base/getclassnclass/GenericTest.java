package com.vinson.base.getclassnclass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.crypto.Cipher;

public class GenericTest {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Cipher c = getInstance(Cipher.class, "AES/CBC/PKCS5Padding");
		System.out.println("sssss");
	}
	
	public static <T> T getInstance(Class<T> clazz, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		T t = null;
		for (Method m : clazz.getDeclaredMethods()) {
			if (m.getName() == "getInstance" && m.getParameterCount() == args.length) {
				t = (T)m.invoke(clazz, args);
				break;
			}
		}
		
		return t;
	}
}
