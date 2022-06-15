package com.css.interview.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.junit.Assert;
import org.junit.Test;

import com.css.interview.event.Event;
import com.css.interview.event.EventManager;
import com.css.interview.model.Courier;
import com.css.interview.model.Order;
import com.css.interview.running.CourierGenerator;

import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Tested;

public class CourierServiceTest {

	@Tested CourierService courierService;
	@Mocked CourierGenerator couierGenerator;
	
	@Test
	public void testOrderReceived() {
		Order order = new Order();
		long time = System.currentTimeMillis();
		Event event = Event.builder().type(Event.Type.ORDER_RECEIVED).timestamp(time).target(order).build();
		
		Courier courier = Courier.builder().build();
		new Expectations() {
			{couierGenerator.findCourier(order); result=courier;}
		};
		
		courierService.handle(event);
		Assert.assertEquals(courier.getDispatchedTimestamp(), time);
		Assert.assertNotNull(courierService.getCouriers());
		Assert.assertTrue(courierService.getCouriers().size() == 1);
		Assert.assertTrue(courierService.getCouriers().get(0) == courier);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testOrderPrepared() throws Exception {
		Order order = new Order();
		long time = System.currentTimeMillis();
		Event event = Event.builder().type(Event.Type.ORDER_PREPARED).timestamp(time).target(order).build();
		
		
		// specified order and has specific courier waiting
		order.setSpecified(true);
		order.setId("orderId");
		Courier courier = Courier.builder().orderId(order.getId()).build();
		List<Courier> couriorList = getPrivateVar("specifiedCourierWaitingList", courierService, List.class);
		couriorList.add(courier);
		new MockUp<EventManager>() {
			@SuppressWarnings("unused")
			void dispatch(Event event) {
				Assert.assertTrue(event.getType() == Event.Type.ORDER_PICKEDUP);
				Assert.assertTrue(event.getTimestamp() == time);
				Assert.assertTrue(event.getTarget() == order);
			}
		};
		courierService.handle(event);
		Assert.assertEquals(time, courier.getPickedupTimestamp());
		Assert.assertEquals(order.getState(), Order.State.PICKEDUP);
		couriorList.clear();
		
		// specified order and has no specific courier in waiting list
		order.setSpecified(true);
		order.setId("orderId");
		couriorList = getPrivateVar("specifiedCourierWaitingList", courierService, List.class);
		List<Order> orderList = getPrivateVar("preparedOrders", courierService, List.class);
		
		courierService.handle(event);
		Assert.assertEquals(orderList.size(), 1);
		Assert.assertEquals(orderList.get(0), order);
		
		// arbitrary order and has courier (no order associated) in waiting queue
		order.setSpecified(false);
		BlockingQueue<Courier> courierQueue = getPrivateVar("courierWaitingQueue", courierService, BlockingQueue.class);
		courier = Courier.builder().build();
		courierQueue.add(courier);
		courierService.handle(event);
		Assert.assertEquals(time, courier.getPickedupTimestamp());
		Assert.assertEquals(order.getState(), Order.State.PICKEDUP);
	}
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCourierArrived() throws Exception {
		Courier courier = Courier.builder().build();
		long time = System.currentTimeMillis();
		Event event = Event.builder().type(Event.Type.COURIDER_ARRIVED).timestamp(time).target(courier).build();
		
		// specific courier, and has order in ready  list
		courier.setOrderId("orderId");
		Order order = new Order();
		order.setSpecified(true);
		order.setId("orderId");
		List<Order> orderList = getPrivateVar("preparedOrders", courierService, List.class);
		orderList.add(order);
		courierService.handle(event);
		Assert.assertEquals(time, courier.getArrivedTimestamp());
		Assert.assertEquals(time, courier.getPickedupTimestamp());
		Assert.assertEquals(order.getState(), Order.State.PICKEDUP);
		orderList.clear();

		// specific courier, and has no order in ready  list
		courier = Courier.builder().build();
		event = Event.builder().type(Event.Type.COURIDER_ARRIVED).timestamp(time).target(courier).build();
		courier.setOrderId("orderId");
		List<Courier> courierList = getPrivateVar("specifiedCourierWaitingList", courierService, List.class);
		Assert.assertTrue(courierList.size() == 0);
		courierService.handle(event);
		Assert.assertTrue(courierList.size() == 1);
		courierList.clear();
		
		// arbitrary courier, has no other arbitrary courier is waiting and has no order in ready  list
		courier = Courier.builder().build();
		event = Event.builder().type(Event.Type.COURIDER_ARRIVED).timestamp(time).target(courier).build();
		BlockingQueue<Courier> courierQueue = getPrivateVar("courierWaitingQueue", courierService, BlockingQueue.class);
		Assert.assertTrue(courierQueue.size() == 0);
		courierService.handle(event);
		Assert.assertTrue(courierQueue.size() == 1);
		courierQueue.clear();
		
		// arbitrary courier, has no other arbitrary courier is waiting and has order in ready  list
		courier = Courier.builder().build();
		event = Event.builder().type(Event.Type.COURIDER_ARRIVED).timestamp(time).target(courier).build();
		order = new Order();
		order.setId("orderId");
		orderList = getPrivateVar("preparedOrders", courierService, List.class);
		orderList.add(order);
		
		courierService.handle(event);
		Assert.assertEquals(time, courier.getArrivedTimestamp());
		Assert.assertEquals(time, courier.getPickedupTimestamp());
		Assert.assertEquals(order.getState(), Order.State.PICKEDUP);
		orderList.clear();
		
		// arbitrary courier, has other arbitrary courier is waiting and has order in ready list
		courier = Courier.builder().build();
		event = Event.builder().type(Event.Type.COURIDER_ARRIVED).timestamp(time).target(courier).build();
		order = new Order();
		order.setId("orderId");
		orderList = getPrivateVar("preparedOrders", courierService, List.class);
		orderList.add(order);
		
		courierQueue = getPrivateVar("courierWaitingQueue", courierService, BlockingQueue.class);
		Courier otherCouier = Courier.builder().build();
		courierQueue.add(otherCouier); // other courier in queue
		courierService.handle(event);
		
		// other courier picked up the order
		Assert.assertEquals(2, courierQueue.size());
		
	}
	
	
	@SuppressWarnings("unchecked")
	private <T> T getPrivateVar(String varName, Object instance, Class<T> clazz) throws Exception {
		Field f = instance.getClass().getDeclaredField(varName);
		f.setAccessible(true);
		return (T)f.get(instance);
	}
	
}
