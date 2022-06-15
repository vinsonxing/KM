package com.css.interview.util;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.css.interview.model.Order;
import com.fasterxml.jackson.core.type.TypeReference;

public class JsonUtilTest {

	
	@Test
	public void testParse() {
		List<Order> orders = JsonUtil.parse(
				new InputStreamReader(
						JsonUtilTest.class.getClassLoader().getResourceAsStream("dispatch_orders_test.json")),
				new TypeReference<List<Order>>() {});
		Assert.assertTrue(orders.size() == 5);
	}
	
	@Test
	public void testConvertToJson() {
		Order o = new Order();
		o.setId("id");
		o.setName("name");
		o.setPrepTime(10);
		Assert.assertEquals("[{\"id\":\"id\",\"name\":\"name\",\"prepTime\":10,\"state\":\"UNKNOWN\",\"specified\":false,\"receivedTimestamp\":0,\"readyTimestamp\":0,\"pickedupTimestamp\":0}]", JsonUtil.convertToJson(Arrays.asList(o)));
	}
}
