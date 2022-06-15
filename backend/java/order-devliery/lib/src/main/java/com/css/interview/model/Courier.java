package com.css.interview.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Courier {
	
	private String id;
	private String orderId;
	private int rideTime; // unit second;
	private long dispatchedTimestamp;
	private long arrivedTimestamp; // arrive after dispatched 3~15 s
	private long pickedupTimestamp;
	
	public String toString() {
		return String.format("%s", id);
	}
}
