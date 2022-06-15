package com.css.interview.running;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.css.interview.event.Event;
import com.css.interview.event.EventManager;
import com.css.interview.event.IEventHandler;
import com.css.interview.event.IEventManager;
import com.css.interview.exception.CSSException;
import com.css.interview.model.Courier;
import com.css.interview.service.ICourierService;
import com.css.interview.service.IOrderService;
import com.css.interview.service.impl.CourierService;
import com.css.interview.service.impl.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SystemRunner {

	private static final IEventManager eventManager = EventManager.getInstance();
	private static final ICourierService courierService = new CourierService();
	private static final IOrderService orderService = new OrderService();
	private static final IEventHandler statisticsService = new StatisticsService();
	static {
		eventManager.registerHandler(courierService);
		eventManager.registerHandler(orderService);
		eventManager.registerHandler(statisticsService);
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		log.info("Start");
		// generate orders in some thread
		OrderGenerator og = new OrderGenerator();
		ExecutorService orderExec = Executors.newFixedThreadPool(1);
		orderExec.execute(og);
		
		CourierGenerator cg = CourierGenerator.getInstance();
		ExecutorService courierExec = Executors.newFixedThreadPool(1);
		courierExec.execute(cg);
		
		// terminate courier generator thread
		courierExec.shutdown();
		// terminate order generator thread
		orderExec.shutdown();
	}
	
	private static class StatisticsService implements IEventHandler {
		
		private AtomicInteger count = new AtomicInteger(0);
		
		@Override
		public void handle(Event event) throws CSSException {
			
			switch (event.getType()) {
			case ORDER_COMPLETED:
			case COURIER_COMPLETED:
				calculateStatistics();
				break;
				
			default:
				log.debug("Received event: {} by courier service", event.getType());
			}
		}
		
		private void calculateStatistics() {
			if (count.addAndGet(1) != 2) {
				return;
			}
			long foodAvgWaitingTime = orderService.getOrders()
					.parallelStream()
					.map(o -> o.getPickedupTimestamp() - o.getReadyTimestamp())
					.reduce(0l,  Long::sum) / orderService.getOrders().size();
			log.info("The average food waiting time is: {} ms", foodAvgWaitingTime);
		
			long courierAvgWaitingTime = courierService.getCouriers()
					.parallelStream()
					.map(c -> (Courier)c)
					.map(c -> c.getPickedupTimestamp() - c.getArrivedTimestamp())
					.reduce(0l, Long::sum) / courierService.getCouriers().size();
			log.info("The average courier waiting time is: {} ms", courierAvgWaitingTime);
		}
	}
	
}
