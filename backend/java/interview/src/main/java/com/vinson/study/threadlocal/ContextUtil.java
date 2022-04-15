package com.vinson.study.threadlocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.util.CollectionUtils;

public class ContextUtil {
    private static final ThreadLocal<String> userCookie  = new ThreadLocal<>();
    private static final ThreadLocal<Map<String,Object>> threadContext  = new ThreadLocal<>();

    public static final String TENANT_ID = "tenantId";
    public static final String JOB_RETRIES = "jobRetries";
    public static final String REQUEST_ID = "requestId";
    public static final String DEVICE_CONFIG_CHANGED_BY_RDM = "deviceConfigChangedByRdm";

    public static final Pattern SAFE_STRING = Pattern.compile("[\\w\\-]+");
    
    private ContextUtil() { }

    public static void put(String key, String value) {
        ThreadContext.put(key, value); // then the content will be saved in to looge, and which can be shown in ES
        Map<String,Object> contextMap = threadContext.get();
        if(contextMap == null) {
            contextMap = new HashMap<>();
            threadContext.set(contextMap);
        }
        contextMap.put(key, value);
    }
    
    public static void put(String key, Long value) {
        Map<String,Object> contextMap = threadContext.get();
        if(contextMap == null) {
            contextMap = new HashMap<>();
            threadContext.set(contextMap);
        }
        contextMap.put(key, value);
    }
    
    public static Object get(String key) {
        Map<String,Object> contextMap = threadContext.get();
        if(contextMap != null) {
            return contextMap.get(key);
        }
        return null;
    }
    
    public static Map<String,Object> getMap() {
        Map<String,Object> contextMap = threadContext.get();
        if(contextMap != null) {
            return Collections.unmodifiableMap(contextMap);
        }
        return Collections.unmodifiableMap(new HashMap<>());
    }
    
    public static Object remove(String key) {
        Map<String,Object> contextMap = threadContext.get();
        if(contextMap != null) {
            return contextMap.remove(key);
        }
        return null;
    }

    public static void initContext() {
		clear();
		put(REQUEST_ID, "");
        put(TENANT_ID, "");
	}

    public static void reapplyThreadContext() {
        Map<String, Object> map = getMap();
        
        if (CollectionUtils.isEmpty(map))
            return;

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            ThreadContext.put(entry.getKey(), String.valueOf(entry.getValue()));
        }
    }
    
    public static void clear() {
        ThreadContext.clearMap();
        threadContext.remove();
        userCookie.remove();
    }
    
    
    public static void set(Map<String,Object> context) {
        threadContext.set(new HashMap<>());
        if(context != null) {
            threadContext.get().putAll(context);
            reapplyThreadContext();
        }
    }
    
    public static void setUserCookie(String cookie) {
        userCookie.set(cookie);
    }
    
    public static String getUserCookie() {
        return userCookie.get();
    }

	
}