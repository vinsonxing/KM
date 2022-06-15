package com.css.interview.model;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import com.css.interview.event.Event;
import com.css.interview.event.EventManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DelayedCourier implements Runnable, Delayed {
    private Courier courier;
    private long arrivedTimestamp;// Order ready

    public DelayedCourier(Courier courier) {
        this.courier = courier;
        this.arrivedTimestamp = courier.getRideTime() * 1000 + System.currentTimeMillis();
    }

    @Override
    public void run() {
    	log.info("Courier {} is arrived after {} seconds", courier.getId(), courier.getRideTime());
    	EventManager.getInstance().dispatch(Event.builder().target(courier).timestamp(System.currentTimeMillis())
				.type(Event.Type.COURIDER_ARRIVED).build());
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long dd = arrivedTimestamp - System.currentTimeMillis();
        return dd;
    }
    
    public Courier getCourier() {
    	return this.courier;
    }

    @Override
    public int compareTo(Delayed o) {
    	DelayedCourier other = (DelayedCourier) o;
        return courier.getRideTime() >= other.getCourier().getRideTime() ? 1 : -1;
    }

    @Override
    public String toString() {
        return String.format("{}", courier);
    }
}