package com.vinson.study.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StringBuilderTest {
	
	public static void main(String[] args) {
		List<Character> list = Arrays.asList('1', '2', '3');
		String s = list.stream().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		System.out.println(s);
		
		Random random = new Random();
	    String suffix = random.ints('0', 'z' + 1)
	    	      .filter(i -> (i >= '0' && i <= '9') || (i >= 'A' && i <= 'Z'))
	    	      .limit(8)
	    	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	    	      .toString();
	    System.out.println(suffix);
	    
	    StringBuilder sb = new StringBuilder();
	    sb.append("1");
	    sb.appendCodePoint(67);
	    sb.append("2");
	    System.out.println(sb.toString());
	}

}
