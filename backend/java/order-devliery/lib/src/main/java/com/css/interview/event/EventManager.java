package com.css.interview.event;

import java.util.ArrayList;
import java.util.List;

import com.css.interview.exception.CSSException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventManager implements IEventManager {
	private final static IEventManager eventManager = new EventManager();
	
	List<IEventHandler> handlers = new ArrayList<>();
	
	private EventManager() {
	}
	
	public static IEventManager getInstance() {
		return eventManager;
	}
	
	public void dispatch(Event event) {
		try {
			
			for (IEventHandler h : handlers) {
				h.handle(event);
			}
		} catch (CSSException e) {
			log.error(String.format("Error to handle event: %s", event.getType()), e);
		}
	}
	
	public void registerHandler(IEventHandler handler) {
		handlers.add(handler);
	}
}
