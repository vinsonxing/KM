package com.vinson.logging;

import org.apache.logging.log4j.ThreadContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
	
	@Test
	public void testDynamicThresholdFilter() {
		Logger logger = LoggerFactory.getLogger(LogTest.class);
		logger.debug("this is a debug log - should not be printed");
		logger.error("this is a error log");
		logger.info("this is a info log");
		ThreadContext.put("X-LOG-DEBUG", "true");
		System.out.println("-->"+ThreadContext.get("X-LOG-DEBUG"));
		if (logger.isDebugEnabled()) {
			logger.debug("this is a debug log with dynamic threshold filter enabled - should be printed");
		}
	}

}
