package com.css.interview.event;

import com.css.interview.exception.CSSException;

public interface IEventHandler {

	void handle(Event event) throws CSSException;
}
