package com.css.interview.util;

import org.junit.Assert;
import org.junit.Test;

public class RandomUtilTest {

	@Test
	public void testNextInt() {
		int value = 0;
		for (int i = 0; i < 100; i++) {
			value = RandomUtil.nextInt(3, 12);
			Assert.assertTrue(value >= 3);
			Assert.assertTrue(value <= 12);
		}
		
	}
}
