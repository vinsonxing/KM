package com.css.interview.service;

import java.util.List;

import com.css.interview.event.IEventHandler;
import com.css.interview.model.Order;

/**
 * Record the order status according to received order events
 */
public interface IOrderService extends IEventHandler {
	
	List<Order> getOrders();
}
