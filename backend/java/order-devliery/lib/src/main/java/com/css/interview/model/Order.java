package com.css.interview.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {
	
	public static enum State {
		UNKNOWN, PREPARING, READY, PICKEDUP
	}
	
	private String id;
	private String name;
	private int prepTime;
	private State state = State.UNKNOWN;
	private boolean specified;
	private long receivedTimestamp;
	private long readyTimestamp;
	private long pickedupTimestamp;
	
	public String toString() {
		return String.format("%s-%s-%s", id, name, specified ? "Specified" : "Arbitrary");
	}
}
