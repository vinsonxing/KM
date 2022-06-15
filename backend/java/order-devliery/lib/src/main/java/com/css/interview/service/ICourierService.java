package com.css.interview.service;

import java.util.List;

import com.css.interview.event.IEventHandler;
import com.css.interview.model.Courier;

/**
 * /**
 * Process courier relevant events:
 * ORDER_RECEIVED, we need to dispatch a courier for this order
 * ORDER_PREPARED, we need to notify the waiting couriers to pickup, or put it to order ready list if no courier waiting
 * COURIDER_ARRIVED, we need to pickup an order for the arrived courier
 *  
 */
public interface ICourierService extends IEventHandler {
	
	List<Courier> getCouriers();
}
