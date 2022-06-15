package com.css.interview.running;

import java.util.UUID;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.css.interview.model.Courier;
import com.css.interview.model.DelayedCourier;
import com.css.interview.model.Order;
import com.css.interview.util.RandomUtil;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CourierGenerator implements Runnable {
	private final static CourierGenerator INSTANCE = new CourierGenerator();
	DelayQueue<DelayedCourier> delayQueue = new DelayQueue<DelayedCourier>();

	
	private CourierGenerator() {
	}
	
	public static CourierGenerator getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Find an available courier. Some couriers are specified orders, and the other not.
	 * 
	 * @param order
	 * @return
	 */
	@Synchronized
	public Courier findCourier(Order order) {
		Courier courier = null;
		String id = getId();
		long time = System.currentTimeMillis();
		int rideTime = getRideTime();
		if (order.isSpecified()) { // arbitrary orders
			courier = Courier.builder().id(id).dispatchedTimestamp(time).orderId(order.getId()).rideTime(rideTime).build();
		} else {
			courier = Courier.builder().id(id).dispatchedTimestamp(time).rideTime(rideTime).build();
		}
		delayQueue.add(new DelayedCourier(courier));
		log.debug("Find courier: {}", courier);
		return courier;
	}
	
	/*
	 * Generate random ride time between 3 and 15 seconds
	 */
	int getRideTime() {
		int rideTime = RandomUtil.nextInt(3, 15);
		return rideTime;
	}
	
	String getId() {
		String id = UUID.randomUUID().toString();
		return id;
	}
	
	@Override
	public void run() {
		CourierProcessor cp = new CourierProcessor(delayQueue);
		ExecutorService exec = Executors.newFixedThreadPool(1);
		exec.execute(cp);
		exec.shutdown();
	}
}
