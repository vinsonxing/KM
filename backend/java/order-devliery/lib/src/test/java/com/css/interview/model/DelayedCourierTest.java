package com.css.interview.model;

import java.util.concurrent.DelayQueue;

import org.junit.Assert;
import org.junit.Test;

import com.css.interview.event.Event;
import com.css.interview.event.EventManager;

import mockit.MockUp;

public class DelayedCourierTest {

	DelayQueue<DelayedCourier> delayQueue = new DelayQueue<>();
	
	@Test
	public void testRun() throws InterruptedException {
		Courier courier = Courier.builder().id("id").rideTime(1).build();
		
		DelayedCourier dc = new DelayedCourier(courier);
		delayQueue.add(dc);
		
		new MockUp<EventManager>() {
			@SuppressWarnings("unused")
			void dispatch(Event event) {
				Assert.assertTrue(event.getType() == Event.Type.COURIDER_ARRIVED);
				Assert.assertTrue(event.getTimestamp() > 0l);
				Assert.assertTrue(event.getTarget() == courier);
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
