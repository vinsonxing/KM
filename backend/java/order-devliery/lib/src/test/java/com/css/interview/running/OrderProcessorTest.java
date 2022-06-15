package com.css.interview.running;

import java.util.concurrent.DelayQueue;

import org.junit.Assert;
import org.junit.Test;

import com.css.interview.event.Event;
import com.css.interview.event.EventManager;
import com.css.interview.model.DelayedOrder;
import com.css.interview.model.Order;

import mockit.Mock;
import mockit.MockUp;

public class OrderProcessorTest {

	OrderProcessor orderProcessor = new OrderProcessor(new DelayQueue<DelayedOrder>());
	
	@Test
	public void testRun() {
		Order order = new Order();
		order.setSpecified(true);
		order.setId("orderId");
		orderProcessor.delayQueue.add(new DelayedOrder(order));
		
		new MockUp<EventManager>() {
			@Mock void dispatch(Event event) {
				Assert.assertTrue(event.getType() == Event.Type.ORDER_COMPLETED || event.getType() == Event.Type.ORDER_PREPARED);
			}
		};
		
		orderProcessor.run();
	}
}
