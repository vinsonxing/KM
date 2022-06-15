package com.css.interview.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {
	public static enum Type {
		ORDER_RECEIVED, ORDER_PREPARED, COURIER_DISPATCHED, COURIDER_ARRIVED, ORDER_PICKEDUP, ORDER_COMPLETED, COURIER_COMPLETED;
	}
	
	private Type type;
	private long timestamp;
	private Object target;
	
}
