package com.vinson.study.datastracture.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapMethods {

	// Main method
	public static void main(String[] args) {
		
		testPutIfAbsent();
		
	}
	
	/**
	 * put value if there is key found in map,
	 * not put value if the key exist,
	 * if key exists and the value of it is not null, return the putting value.
	 * if succeed (exits a key with 'null' value or doesn't exist a key), return null
	 */
	private static void testPutIfAbsent() {
		// create a HashMap and add some values
		Map<String, Integer> map = new HashMap<>();
		map.put("a", 10000);
		map.put("b", 55000);
		map.put("c", 44300);
		map.put("e", 53200);

		// print original map
		System.out.println("HashMap:\n " + map.toString());

		// put a new value which is not mapped
		// before in map
		System.out.println(map.putIfAbsent("d", 77633));

		System.out.println("New HashMap:\n " + map);

		// try to put a new value with existing key
		// before in map
		System.out.println(map.putIfAbsent("d", 55555));

		// print newly mapped map
		System.out.println("Unchanged HashMap:\n " + map);
		
		map.putIfAbsent(null, null);
//		map.putIfAbsent(null, 1234);
		
		System.out.println("HashMap:\n " + map);
	}
	
	private static void testCompulate() {
		
	}
}
