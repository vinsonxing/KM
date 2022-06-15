package com.css.interview.running;

import java.util.concurrent.DelayQueue;

import org.junit.Assert;
import org.junit.Test;

import com.css.interview.event.Event;
import com.css.interview.event.EventManager;
import com.css.interview.model.Courier;
import com.css.interview.model.DelayedCourier;
import com.css.interview.model.Order;

import mockit.Mock;
import mockit.MockUp;

public class CourierProcessorTest {

	CourierProcessor couierProcessor = new CourierProcessor(new DelayQueue<DelayedCourier>());
	
	@Test
	public void testRun() {
		CourierGenerator courierGenerator = CourierGenerator.getInstance();
		Order order = new Order();
		order.setSpecified(true);
		order.setId("orderId");
		Courier courier = courierGenerator.findCourier(order);
		couierProcessor.delayQueue.add(new DelayedCourier(courier));
		
		new MockUp<EventManager>() {
			@Mock void dispatch(Event event) {
				Assert.assertTrue(event.getType() == Event.Type.COURIER_COMPLETED || event.getType() == Event.Type.COURIDER_ARRIVED);
			}
		};
		
		couierProcessor.run();
		
	}
}
