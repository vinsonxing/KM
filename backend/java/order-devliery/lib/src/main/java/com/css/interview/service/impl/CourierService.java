package com.css.interview.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

import org.apache.logging.log4j.util.Strings;

import com.css.interview.event.Event;
import com.css.interview.event.EventManager;
import com.css.interview.model.Courier;
import com.css.interview.model.Order;
import com.css.interview.running.CourierGenerator;
import com.css.interview.service.ICourierService;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

/**
 * Process courier relevant events:
 * ORDER_RECEIVED, we need to dispatch a courier for this order
 * ORDER_PREPARED, we need to notify the waiting couriers to pickup, or put it to order ready list if no courier waiting
 * COURIDER_ARRIVED, we need to pickup an order for the arrived courier
 */
@Slf4j
public class CourierService implements ICourierService {

	private CourierGenerator courierGenerator = CourierGenerator.getInstance();
	private List<Courier> couriers = Collections.synchronizedList(new ArrayList<>());
	private BlockingQueue<Courier> courierWaitingQueue = new ArrayBlockingQueue<>(100);
	private List<Courier> specifiedCourierWaitingList = Collections.synchronizedList(new ArrayList<>());
	private List<Order> preparedOrders = Collections.synchronizedList(new ArrayList<>());
	
	/**
	 * Process courier relevant events, ORDER_RECEIVED, ORDER_PREPARED, COURIDER_ARRIVED
	 */
	@Override
	public void handle(Event event) {
		switch (event.getType()) {
		case ORDER_RECEIVED:
			handleOrderReceived(event);
			break;
		case ORDER_PREPARED:
			handleOrderPrepated(event);
			break;
		case COURIDER_ARRIVED:
			handleCourierArrived(event);
			break;
		default:
			log.debug("Received event: {} by courier service", event.getType());
		}
	}

	@Override
	public List<Courier> getCouriers() {
		return couriers;
	}
	
	/*
	 * Assign a courier for a give order, and set the courier dispatched time
	 */
	@Synchronized
	private void handleOrderReceived(Event event) {
		log.debug("Processing event: {} by courier service", event.getType());
		Order order = (Order)event.getTarget();
		Courier courier = courierGenerator.findCourier(order);
		courier.setDispatchedTimestamp(event.getTimestamp());
		couriers.add(courier);
		log.info("A {} order {} is dispatched to courier {}", order.isSpecified() ? "specified" : "arbitrary" , order, courier);
		log.debug("Processed event: {} by courier service", event.getType());
	}
	
	/*
	 * check if courier already arrived, if yes, notify the corresponding courier to pick it
	 * up, otherwise move it a waiting list, so that the courier can pick it up later
	 */
	@Synchronized
	private void handleOrderPrepated(Event event) {
		log.debug("Processing event: {} by courier service", event.getType());
		Order order = (Order)event.getTarget();
		long time = event.getTimestamp();
		order.setReadyTimestamp(time);
		if (order.isSpecified()) {
			Courier courier = activeCourier(order);
			if (courier != null) {
				completeOrder(order, courier, time);
			} else {
				preparedOrders.add(order);
			}
		} else {
			preparedOrders.add(order);
			log.debug("Couriers: {}", courierWaitingQueue);
			// find one courier from waiting queue
			if (!courierWaitingQueue.isEmpty()) {
				Courier courier = courierWaitingQueue.remove();
				Order pOrder = pickupOrder(order.isSpecified(), courier, "order-prepared");
				if (Objects.nonNull(pOrder)) {
					completeOrder(pOrder, courier, time);
				}
			}
		}
		
		log.debug("Processed event: {} by courier service", event.getType());
	}
	
	/*
	 *  check the order prepared list to pick up an order.
	 */
	@Synchronized
	private void handleCourierArrived(Event event) {
		log.debug("Processing event: {} by courier service", event.getType());
		Courier courier = (Courier)event.getTarget();
		boolean isSpecifiedOrder = isCourierSpecifiedAnOrder(courier);
		long time = event.getTimestamp();
		courier.setArrivedTimestamp(time);
		
		// if specified, pick up order from the prepared order list directly if exist
		if (isSpecifiedOrder) {
			tryPickupOrder(true, courier, time, c -> {
				specifiedCourierWaitingList.add(c);
			});
		} else {			
			// if arbitrary, check the courier waiting queue first
			if (courierWaitingQueue.isEmpty()) {
				tryPickupOrder(false, courier, time, c -> {
					courierWaitingQueue.add(c);
				});
			} else {
				log.debug("Other couriers are waiting for the order, join the waiting queue");
				courierWaitingQueue.add(courier);
			}
		}
		
		log.debug("Processed event: {} by courier service", event.getType());
	}
	
	private boolean isCourierSpecifiedAnOrder(Courier courier) {
		return Strings.isNotEmpty(courier.getOrderId());
	}
	
	private void tryPickupOrder(boolean specified, Courier courier, long time, Consumer<Courier> orElse) {
		Order order = pickupOrder(specified, courier, "courier-arrived");
		// pick up the order, and the order is done
		if (Objects.nonNull(order)) {
			completeOrder(order, courier, time);
		} else {
			if (orElse != null) {
				log.debug("Courier {} has arrived, but {} order hasn't been ready, wating for order", courier.getId(), specified ? "the specified" : "no" );
				orElse.accept(courier);
			}
		}

	}
	
	/*
	 * Pick the specific order if the courier is specified to an order
	 * Pick the first valid order if the courier isn't specified to an order
	 */
	private Order pickupOrder(boolean specified, Courier courier, String trigger) {
		if (preparedOrders.size() == 0) return null;
		Iterator<Order> itOrder = preparedOrders.iterator();
		Order order = null;
		Order tmp = null;

		while(itOrder.hasNext()) {
			tmp = itOrder.next();
			if (specified && tmp.isSpecified() && tmp.getId().equals(courier.getOrderId())) {
				itOrder.remove();
				order = tmp;
				break;
			} else if (!specified && !tmp.isSpecified()) {
				itOrder.remove();
				order = tmp;
				break;
			}
			log.debug("Courier: {}, Orders: {}, trigger: {}, order: {}", courier, preparedOrders, trigger, courier.getOrderId());
		}
		
		return order;
	}
	
	private Courier activeCourier(Order order) {
		Iterator<Courier> itCourier = specifiedCourierWaitingList.iterator();
		Courier tmp = null;
		Courier courier = null;
		while(itCourier.hasNext()) {
			tmp = itCourier.next();
			if (order.getId().equals(tmp.getOrderId())) {
				itCourier.remove();
				courier = tmp;
				break;
			}
		}
		
		return courier;
	}
	
	private void completeOrder(Order order, Courier courier, long time) {		
		courier.setPickedupTimestamp(time);
		order.setPickedupTimestamp(time);
		order.setState(Order.State.PICKEDUP);
		EventManager.getInstance().dispatch(Event.builder().type(Event.Type.ORDER_PICKEDUP).target(order).timestamp(time).build());
		log.info("{} order {} is picked up by {}, the order is completed", order.isSpecified() ? "Specified" : "Arbitrary" , order, courier);
	}
	

}
