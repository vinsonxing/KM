package com.css.interview.running;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;

import com.css.interview.event.Event;
import com.css.interview.event.EventManager;
import com.css.interview.model.DelayedOrder;
import com.css.interview.util.ThreadPoolUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Maintain the order generator to make sure the orders can be prepared according to the prepTime in order
 */
@Slf4j
public class OrderProcessor implements Runnable {

	DelayQueue<DelayedOrder> delayQueue = null;
	
	public OrderProcessor(DelayQueue<DelayedOrder> delayQueue) {
		this.delayQueue = delayQueue;
	}
	
	@Override
	public void run() {
		try {
			// process the orders
			log.info("Start to process order queue");
			int count = process();
			log.info("Have processed {} orders", count);
			EventManager.getInstance().dispatch(Event.builder().type(Event.Type.ORDER_COMPLETED).build());
		} catch (InterruptedException e) {
			log.error("Error to process order", e);
		}
	}
	
	private int process() throws InterruptedException {
		ExecutorService es = ThreadPoolUtil.getExector();
		int processedCount = 0;
		DelayedOrder dOrder = null;
		do {
			dOrder = delayQueue.take();
			es.execute(dOrder);
			processedCount ++;
		} while(!delayQueue.isEmpty());
		// terminate the thread pool
		es.shutdown();
		return processedCount;
	}
}
