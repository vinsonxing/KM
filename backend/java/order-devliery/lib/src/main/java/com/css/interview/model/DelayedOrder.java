package com.css.interview.model;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import com.css.interview.event.Event;
import com.css.interview.event.EventManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DelayedOrder implements Runnable, Delayed {
    private Order order;
    private long readyTimestamp;// Order ready

    public DelayedOrder(Order order) {
        this.order = order;
        this.readyTimestamp = order.getPrepTime() * 1000 + System.currentTimeMillis();
    }

    @Override
    public void run() {
    	log.info("Order {} is ready, cooking for {} seconds", order, order.getPrepTime());
    	EventManager.getInstance().dispatch(Event.builder().target(order).timestamp(System.currentTimeMillis())
				.type(Event.Type.ORDER_PREPARED).build());
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long dd = readyTimestamp - System.currentTimeMillis();
        return dd;
    }
    
    public Order getOrder() {
    	return this.order;
    }

    @Override
    public int compareTo(Delayed o) {
    	DelayedOrder other = (DelayedOrder) o;
        return order.getPrepTime() >= other.getOrder().getPrepTime() ? 1 : -1;
    }

    @Override
    public String toString() {
        return String.format("{}", order);
    }
}