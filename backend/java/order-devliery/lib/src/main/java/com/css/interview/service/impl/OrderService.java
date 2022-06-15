package com.css.interview.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import com.css.interview.event.Event;
import com.css.interview.model.Order;
import com.css.interview.service.IOrderService;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

/**
 * Record the order status according to received order events
 */
@Slf4j
public class OrderService implements IOrderService {

	private List<Order> orders = Collections.synchronizedList(new ArrayList<>());
	
	/**
	 * Process order relevant events, ORDER_RECEIVED, ORDER_PREPARED, ORDER_PICKEDUP
	 */
	@Override
	public void handle(Event event) {
		switch (event.getType()) {
		case ORDER_RECEIVED: // set state and receive time
			handleOrderReceived(event);
			break;
		case ORDER_PREPARED: // set state and prepared time
			handleOrderPrepared(event);
			break;
		case ORDER_PICKEDUP: // set state and pick up time
			handleOrderPickedUp(event);
			break;
		default:
			log.debug("Reveived event: {} by order service", event.getType());
		}
	}
	
	@Override
	public List<Order> getOrders() {
		return orders;
	}
	
	private void handleOrderReceived(Event event) {
		handleEvent(event, o -> {
			o.setState(Order.State.PREPARING);
			o.setReceivedTimestamp(event.getTimestamp());
			orders.add(o);
		});
	}
	
	private void handleOrderPrepared(Event event) {
		handleEvent(event, o -> {
			o.setState(Order.State.READY);
			o.setReadyTimestamp(event.getTimestamp());
		});
	}
	
	private void handleOrderPickedUp(Event event) {
		handleEvent(event, o -> {
			o.setState(Order.State.PICKEDUP);
			o.setPickedupTimestamp(event.getTimestamp());
		});
	}
	
	@Synchronized
	private void handleEvent(Event event, Consumer<Order> consumer) {
		log.debug("Processing event: {} by order service", event.getType());
		Order order = (Order)event.getTarget();
		consumer.accept(order);
		log.debug("Processed event: {} by order service", event.getType());
	}
}
