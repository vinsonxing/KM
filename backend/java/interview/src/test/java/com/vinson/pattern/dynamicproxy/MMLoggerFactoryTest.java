package com.vinson.pattern.dynamicproxy;

import org.apache.logging.log4j.ThreadContext;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class MMLoggerFactoryTest {

	@Before
	public void init() {
		ThreadContext.put("forcePrintDebugLog", "false");
	}
	
	@Test
	public void testLoggerFromClass() {
		Logger logger = MMLoggerFactory.getLogger(MMLoggerFactoryTest.class);
		logger.trace("this is trace log - should not be printed");
		logger.debug("this is debug log - should not be printed");
		logger.info("this is info log");
		logger.warn("this is warn log");
		logger.error("this is error log");
		
		ThreadContext.put("forcePrintDebugLog", "true");
		
		logger.trace("this is trace log - should not be printed");
		logger.debug("DEBUG-this is debug log - after proxy");
		logger.info("this is info log");
		logger.warn("this is warn log");
		logger.error("this is error log");
	}
	
	@Test
	public void testLoggerFromName() {
		Logger logger = MMLoggerFactory.getLogger("MMLoggerFactoryTest");
		logger.trace("this is trace log - should not be printed");
		logger.debug("this is debug log - should not be printed");
		logger.info("this is info log");
		logger.warn("this is warn log");
		logger.error("this is error log");
		
		ThreadContext.put("forcePrintDebugLog", "true");
		
		logger.trace("this is trace log - should not be printed");
		logger.debug("DEBUG-this is debug log - after proxy");
		logger.info("this is info log");
		logger.warn("this is warn log");
		logger.error("this is error log");
	}
	
	@Test
	public void testLogWithParameters() {
		Logger logger = MMLoggerFactory.getLogger("MMLoggerFactoryTest");
		ThreadContext.put("forcePrintDebugLog", "true");
		logger.debug("DEBUG-the parameter one is: {}", "one");
		logger.debug("DEBUG-the parameter one is: {}, two is: {}", "one", "two");
		logger.debug("DEBUG-the parameter one is: {}, two is: {}, three: {}", "one", "two", "three");
	}
	
	@Test
	public void testLogWithThrowable() {
		Logger oriLogger = LoggerFactory.getLogger("MMLoggerFactoryTest-original");
		Logger logger = MMLoggerFactory.getLogger("MMLoggerFactoryTest");
		ThreadContext.put("forcePrintDebugLog", "true");

		logger.debug("DEBUG-the operation met exception: {}", new Exception("some exception"));
		oriLogger.info("the operation met exception: {}", new Exception("some exception"));
		
		logger.debug("DEBUG-the operation for '{}' met exception: {}", "CREATE", new Exception("some exception"));
		oriLogger.info("the operation for '{}' or '{}'  met exception: {}", "CREATE", "UPDATE", new Exception("some exception"));
		
		logger.debug("DEBUG-the operation for '{}' or '{}'  met exception: {}", "CREATE", "UPDATE", new Exception("some exception"));
	}
	
	
	@Test
	public void testIsDebugEnabled() {
		Logger logger = MMLoggerFactory.getLogger("MMLoggerFactoryTest");
		ThreadContext.put("forcePrintDebugLog", "true");
		if(logger.isDebugEnabled()) {
			logger.debug("DEBUG-this is debug message with condition check");
		}
	}
	
//  public void info(Marker marker, String msg);
//  public void info(Marker marker, String format, Object arg);
//  public void info(Marker marker, String format, Object arg1, Object arg2);
//  public void info(Marker marker, String format, Object... arguments);
//  public void info(Marker marker, String msg, Throwable t);
	
	@Test
	public void testLogWithMarker() {
		Logger logger = MMLoggerFactory.getLogger("MMLoggerFactoryTest");
	    Marker marker = MarkerFactory.getMarker("CONFIDENTIAL");
	    ThreadContext.put("forcePrintDebugLog", "true");
	    
	    logger.debug(marker, "DEBUG-this is a confidential message");
	    logger.debug(marker, "DEBUG-this is a confidential message with param1: {}", "one");
	    logger.debug(marker, "DEBUG-this is a confidential message with param1: {}, param2: {}", "one", "two");
	    logger.debug(marker, "DEBUG-this is a confidential message with param1: {}, param2: {}, param3: {}", "one", "two", "three");
	    
	    logger.debug(marker, "DEBUG-this is a confidential message", new Exception("some exception"));
	    logger.debug(marker, "DEBUG-this is a confidential message with param1: {}", "one", new Exception("some exception"));
	    logger.debug(marker, "DEBUG-this is a confidential message with param1: {}, param2: {}", "one", "two", new Exception("some exception"));
	    logger.debug(marker, "DEBUG-this is a confidential message with param1: {}, param2: {}, param3: {}", "one", "two", "three", new Exception("some exception"));
	    
	 }
	
	
}
