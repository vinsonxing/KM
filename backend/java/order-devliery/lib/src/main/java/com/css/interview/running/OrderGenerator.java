package com.css.interview.running;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.css.interview.event.Event;
import com.css.interview.event.EventManager;
import com.css.interview.model.DelayedOrder;
import com.css.interview.model.Order;
import com.css.interview.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;
/**
 * Order generator. Generate two orders per second, and trigger the order to be preparing
 * The order can be specified and arbitrary, it's randomly, the rate can be 60% (specified) to 40% (arbitrary)
 */
@Slf4j
public class OrderGenerator implements Runnable {
	
	List<Order> orders = new ArrayList<>();
	DelayQueue<DelayedOrder> delayQueue = new DelayQueue<DelayedOrder>();
	
	{
		init();
	}
	
	public OrderGenerator() {
	}
	
	void init() {
		orders = JsonUtil.parse(
				new InputStreamReader(
						OrderGenerator.class.getClassLoader().getResourceAsStream(getOrderResourceFile())),
				new TypeReference<List<Order>>() {});
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public DelayQueue<DelayedOrder> getDelayQueue() {
		return delayQueue;
	}
	
	String getOrderResourceFile() {
		return "dispatch_orders.json";
	}

	@Override
	public void run() {
		startProcess();
		for (int i = 0; i < orders.size(); i = i + 2) {
			enqueue(orders.get(i));
			if ((i + 1) < orders.size()) {
				enqueue(orders.get(i+1));
			}
			try {
				// Wait for 1 second to receive another two orders, per requirement 
				Thread.sleep(1000l);
			} catch (InterruptedException e) {
				// should not be here
				e.printStackTrace();
			}
		}
		log.info("Received {} orders totally today", orders.size());
	}
	
	private void enqueue(Order order) {
		DelayedOrder dOrder = new DelayedOrder(order);
		delayQueue.put(dOrder);
		order.setSpecified(isSpecified());
		log.info("Received order: {}", order);
		EventManager.getInstance().dispatch(Event.builder().type(Event.Type.ORDER_RECEIVED).target(order).timestamp(System.currentTimeMillis()).build());
	}
	
	/*
	 * Order type, specified or arbitrary
	 */
	boolean isSpecified() {
		Random random = new Random();
		int num = random.nextInt(5);
		return num != 1 && num != 3;
	}
	
	private void startProcess() {
		OrderProcessor op = new OrderProcessor(delayQueue);
		ExecutorService exec = Executors.newFixedThreadPool(1);
		exec.execute(op);
		exec.shutdown();
	}
	
}
