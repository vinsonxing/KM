package interview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SlideWindowTest {
	static String getUniqueCharacterSubstring(String input) {
	    Map<Character, Integer> visited = new HashMap<>();
	    String output = "";
	    for (int start = 0, end = 0; end < input.length(); end++) {
	        char currChar = input.charAt(end);
	        if (visited.containsKey(currChar)) {
	            start = Math.max(visited.get(currChar)+1, start);
	            //System.out.println("start is "+start);
	        }
	        if (output.length() < end - start + 1) {
	            output = input.substring(start, end + 1);
	            //System.out.println("output is " + output);
	        }
	        visited.put(currChar, end);
	        //System.out.println("now we put " + currChar + ":" + end);
	    }
	    return output;
	} 
	static String getUniqueCharacterSubstring1(String input) {
	    Set<Character> visited = new HashSet<>();
	    int max = 0;
	    int maxStart = 0, maxEnd = 0;
	    for (int start = 0, end = 0; end < input.length(); end++) {
	        char currChar = input.charAt(end);
	        while (visited.contains(currChar)) {
	        	visited.remove(input.charAt(start));
	        	start++;
	        }
	        if (end - start + 1 > max) {
	        	max = end - start + 1;
	        	maxStart = start;
	        	maxEnd = end;
	        }
	        visited.add(currChar);
	    }
	    return input.substring(maxStart, maxEnd + 1);
	} 
	public static void main(String[] args) {
		String input = "CODINGISAWESOME";
		System.out.println("final result is " + getUniqueCharacterSubstring(input));
		System.out.println("final result is " + getUniqueCharacterSubstring1(input));
		
	}
	
}
