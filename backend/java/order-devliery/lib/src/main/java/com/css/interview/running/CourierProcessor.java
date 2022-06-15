package com.css.interview.running;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;

import com.css.interview.event.Event;
import com.css.interview.event.EventManager;
import com.css.interview.model.DelayedCourier;
import com.css.interview.util.ThreadPoolUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Maintain the courier generator to make sure the dispatched couriers can arrived between 3 and 15 seconds
 */
@Slf4j
public class CourierProcessor implements Runnable {
	DelayQueue<DelayedCourier> delayQueue = null;
	
	public CourierProcessor(DelayQueue<DelayedCourier> delayQueue) {
		this.delayQueue = delayQueue;
	}
	
	@Override
	public void run() {
		log.debug("Start to process courier queue");
		ExecutorService es = ThreadPoolUtil.getExector();
		int processedCount = 0;
		DelayedCourier dCourier = null;
		try {
			do {
				dCourier = delayQueue.take();
				es.execute(dCourier);
				processedCount ++;
			} while(!delayQueue.isEmpty());
			log.info("Have processed {} couriers", processedCount);
			EventManager.getInstance().dispatch(Event.builder().type(Event.Type.COURIER_COMPLETED).build());
		} catch (InterruptedException e) {
			log.error("Erorr to pick courier from queue", e);
		}
		// terminate the thread pool
		es.shutdown();
	}
}
