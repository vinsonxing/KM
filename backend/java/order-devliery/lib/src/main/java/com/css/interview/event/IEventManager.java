package com.css.interview.event;

public interface IEventManager {
	
	public void dispatch(Event event);
	
	public void registerHandler(IEventHandler handler);
	
}
