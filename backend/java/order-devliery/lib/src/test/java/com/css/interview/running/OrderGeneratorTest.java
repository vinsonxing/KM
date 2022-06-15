package com.css.interview.running;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.css.interview.event.Event;
import com.css.interview.event.EventManager;
import com.css.interview.model.Order;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;

public class OrderGeneratorTest {

	@Tested OrderGenerator orderGenerator;
	@Mocked EventManager eventmManger;
	
	@Test
	public void testLoadResourceFile() {
		
		new MockUp<OrderGenerator>() {
			@Mock String getOrderResourceFile() {
				return "dispatch_orders_test.json";
			}
		};
		
		orderGenerator.init();
		List<Order> orders = orderGenerator.getOrders();
		Assert.assertTrue(orders.size() == 5);
	}
	
	@Test
	public void testRun() {
		
		new MockUp<OrderGenerator>() {
			@Mock String getOrderResourceFile() {
				return "dispatch_orders_test.json";
			}
		};
		
		orderGenerator.init();
		orderGenerator.run();
		new Verifications() {
			{eventmManger.dispatch((Event)any); times=5;}
		};
	}
}
