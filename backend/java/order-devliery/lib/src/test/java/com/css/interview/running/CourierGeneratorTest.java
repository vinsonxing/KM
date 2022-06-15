package com.css.interview.running;

import org.junit.Assert;
import org.junit.Test;

import com.css.interview.model.Courier;
import com.css.interview.model.Order;

import mockit.Tested;

public class CourierGeneratorTest {

	@Tested CourierGenerator courierGenerator;
	
	@Test
	public void testFindCourier() {
		Order order = new Order();
		order.setSpecified(true);
		order.setId("orderId");
		Courier courier = courierGenerator.findCourier(order);
		Assert.assertTrue(courier.getOrderId().equals(order.getId()));
		Assert.assertTrue(courier.getRideTime() >= 3);
		Assert.assertTrue(courier.getRideTime() <= 15);
		Assert.assertTrue(courierGenerator.delayQueue.size() == 1);
		Assert.assertTrue(courierGenerator.delayQueue.peek().getCourier() == courier);
		courierGenerator.delayQueue.clear();
	}
	
	@Test
	public void testRun() throws Exception {
		Order order = new Order();
		order.setSpecified(true);
		order.setId("orderId");
		Courier courier = courierGenerator.findCourier(order);
		courier.setRideTime(1);
		Assert.assertTrue(courierGenerator.delayQueue.size() == 1);
		courierGenerator.run();
	}
	
}
