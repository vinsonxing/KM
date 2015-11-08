package com.vinson.util.collection;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollectionTest {

	public static void main(String[] args) {
		test1();
		test2();
	}
	
	private static void test2() {
		AbstractCollection<Child> ac = new ArrayList<>();
		ac.add(new Child());
		ac.add(new Child());
		ac.add(new Child());
		ac.add(new Child());
		
		// the size of the new array should be less than the given collection
		Child[] c = ac.toArray(new Child[4]);
		Parent[] p = ac.toArray(new Parent[4]);
		System.out.println(c);
		System.out.println(p);
	}
	
	private static void test1() {
		AbstractCollection<Integer> ac = new ArrayList<>();
		ac.add(1);
		ac.add(2);
		ac.add(3);
		ac.add(4);
		
		// Only return a raw array without type even the generic type is declared 
		Object[] obj = ac.toArray();
		System.out.println(obj);
		
		// Return the specific type which should be the generic type's subtype of itself.
		Integer[] intArray = ac.toArray(new Integer[3]);
		System.out.println(intArray);
		
		// Error
		/*String[] strArray = ac.toArray(new String[3]);
		System.out.println(strArray);*/
	}
}
