package com.css.interview.model;

import java.util.concurrent.DelayQueue;

import org.junit.Assert;
import org.junit.Test;

import com.css.interview.event.Event;
import com.css.interview.event.EventManager;

import mockit.MockUp;

public class DelayedOrderTest {

DelayQueue<DelayedOrder> delayQueue = new DelayQueue<>();
	
	@Test
	public void testRun() throws InterruptedException {
		Order order = new Order();
		order.setId("id");
		order.setPrepTime(1);
		
		
		
		DelayedOrder oo = new DelayedOrder(order);
		delayQueue.add(oo);
		
		new MockUp<EventManager>() {
			@SuppressWarnings("unused")
			void dispatch(Event event) {
				Assert.assertTrue(event.getType() == Event.Type.ORDER_PREPARED);
				Assert.assertTrue(event.getTimestamp() > 0l);
				Assert.assertTrue(event.getTarget() == order);
			}
		};
		long start = System.currentTimeMillis();
		delayQueue.take();
		long end = System.currentTimeMillis();
		// about 1 second, since the ride time is 1 second
		Assert.assertTrue(end - start > 990);
		Assert.assertTrue(end - start < 1000);
	}
}
