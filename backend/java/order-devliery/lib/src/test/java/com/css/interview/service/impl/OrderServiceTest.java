package com.css.interview.service.impl;

import org.junit.Assert;
import org.junit.Test;

import com.css.interview.event.Event;
import com.css.interview.model.Order;

import mockit.Tested;

public class OrderServiceTest {

	@Tested OrderService orderService;
	
	@Test
	public void testOrderReceive() {
		Order order = new Order();
		long time = System.currentTimeMillis();
		Event event = Event.builder().type(Event.Type.ORDER_RECEIVED).timestamp(time).target(order).build();
		
		orderService.handle(event);
		Assert.assertTrue(time == order.getReceivedTimestamp());
		Assert.assertTrue(Order.State.PREPARING == order.getState());
	}
	
	@Test
	public void testOrderPrepared() {
		Order order = new Order();
		long time = System.currentTimeMillis();
		Event event = Event.builder().type(Event.Type.ORDER_PREPARED).timestamp(time).target(order).build();
		
		orderService.handle(event);
		Assert.assertTrue(time == order.getReadyTimestamp());
		Assert.assertTrue(Order.State.READY == order.getState());
	}
	
	@Test
	public void testOrderPicked() {
		Order order = new Order();
		long time = System.currentTimeMillis();
		Event event = Event.builder().type(Event.Type.ORDER_PICKEDUP).timestamp(time).target(order).build();
		
		orderService.handle(event);
		Assert.assertTrue(time == order.getPickedupTimestamp());
		Assert.assertTrue(Order.State.PICKEDUP == order.getState());
	}
}
