package com.vinson.pattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

// Check the corresponding test for detail
public class MMLoggerFactory {
	
	public static class LoggerHandler implements InvocationHandler {

		private final Logger innerLogger;
		
		public LoggerHandler(Logger logger) {
			this.innerLogger = logger;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			String flag = ThreadContext.get("forcePrintDebugLog");

			// bypass debug level check if flag is true
			if (method.getName().equals("isDebugEnabled")) {
				return ((Boolean)method.invoke(innerLogger, args)) || "true".equals(flag);
			}
			
			// force print debug level as info level log if flag is true
			if ("true".equals(flag) && method.getName().equals("debug")) {
				Method[] methods = innerLogger.getClass().getMethods();
				Method targetMethod = getMethodByName(methods, "info", args);
				if (null != targetMethod) {
					method = targetMethod;
				}
			}
			System.out.println("--->" + method.toGenericString());
			return method.invoke(innerLogger, args);
		}
		
	} 

	private static Method getMethodByName(Method[] methods, String name, Object[] args) {
		for (Method m : methods) {
			if (m.getName().equals(name)
					&& args.length > 0
					&& m.getParameterCount() == args.length) {
				if (typeMatch(args, m.getParameterTypes())) {
					return m;
				}
			}
		}
		return null;
	}
	
	
//	  public void info(String msg);
//    public void info(String format, Object arg);
//    public void info(String format, Object arg1, Object arg2);
//    public void info(String format, Object... arguments);
//    public void info(String msg, Throwable t);
//    
//    public void info(Marker marker, String msg);
//    public void info(Marker marker, String format, Object arg);
//    public void info(Marker marker, String format, Object arg1, Object arg2);
//    public void info(Marker marker, String format, Object... arguments);
//    public void info(Marker marker, String msg, Throwable t);
	
	private static boolean typeMatch(Object[] args, Class<?>[] clazzes) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].getClass() == clazzes[i]) continue;
			if (args[i].getClass().isArray() != clazzes[i].isArray()) {
				return false;
			}
			if (!clazzes[i].isAssignableFrom(args[i].getClass())) return false;
		}
		return true;
	}
	
	public static Logger getLogger(String name) {
		Logger logger = LoggerFactory.getLogger(name);
		return wrapLogger(logger);
	}
	
	public static Logger getLogger(Class<?> clazz) {
		Logger logger = LoggerFactory.getLogger(clazz);
		return wrapLogger(logger);
	}
	
	
	private static Logger wrapLogger(Logger targetLogger) {
		LoggerHandler lHandler = new LoggerHandler(targetLogger);
		return (Logger) Proxy.newProxyInstance(Logger.class.getClassLoader(), new Class[] {Logger.class}, lHandler);
	}
}

