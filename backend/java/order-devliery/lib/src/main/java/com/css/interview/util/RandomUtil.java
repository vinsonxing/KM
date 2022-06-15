package com.css.interview.util;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomUtil {

	public static int nextInt(int startBoundary, int endBoundary) {
		Random random = new Random();
		return random.ints(startBoundary, endBoundary+1).findFirst().getAsInt();
	}
	
	public static void main(String[] args) {
		IntStream.range(0, 100).forEach(i -> System.out.println(RandomUtil.nextInt(3, 15)));
	}
	
}
