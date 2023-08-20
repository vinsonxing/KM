package interview;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
	public static Character getFirstAppearingOnce(String input) {
		HashMap<Character, Boolean> res = new LinkedHashMap<>();
		for(char n:input.toCharArray()) {
			res.put(n, !res.containsKey(n));
		}
		for(Map.Entry<Character, Boolean> entry:res.entrySet()) {
			if(entry.getValue()) return entry.getKey();
		}
		return ' ' ;
	}
	
	public static void main(String[] args) {
		String input = "abbadefe";
		System.out.println(getFirstAppearingOnce(input));
	}
}
 