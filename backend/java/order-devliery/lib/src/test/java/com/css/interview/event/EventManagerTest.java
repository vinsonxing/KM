package com.css.interview.event;

import org.junit.Assert;
import org.junit.Test;

import com.css.interview.exception.CSSException;
import com.css.interview.service.impl.CourierService;
import com.css.interview.service.impl.OrderService;

import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;

public class EventManagerTest {

	@Tested EventManager eventManager;
	@Injectable OrderService orderService;
	@Injectable CourierService courierService;
	
	@Test
	public void testDispatch() throws CSSException {
		IEventManager em = EventManager.getInstance();
		em.registerHandler(orderService);
		em.registerHandler(courierService);
		Event e = Event.builder().type(Event.Type.ORDER_RECEIVED).build();
		em.dispatch(e);
		
		new Verifications() {
			{orderService.handle((Event)any); times=1;}
			{courierService.handle((Event)any); times=1;}
		};
	}
	
	@Test
	public void testRegisterHandler() {
		eventManager.registerHandler(orderService);
		eventManager.registerHandler(courierService);
		Assert.assertTrue(eventManager.handlers.size() == 2);
	}
}
