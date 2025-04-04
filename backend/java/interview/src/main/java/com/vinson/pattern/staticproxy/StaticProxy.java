package com.vinson.pattern.staticproxy;

public class StaticProxy {

	
	public interface SmsService {
	    String send(String message);
	}
	
	public static class SmsServiceImpl implements SmsService {
	    public String send(String message) {
	        System.out.println("send message:" + message);
	        return message;
	    }
	}
	
	public static class SmsProxy implements SmsService {

	    private final SmsService smsService;

	    public SmsProxy(SmsService smsService) {
	        this.smsService = smsService;
	    }

	    @Override
	    public String send(String message) {
	        //调用方法之前，我们可以添加自己的操作
	        System.out.println("before method send()");
	        smsService.send(message);
	        //调用方法之后，我们同样可以添加自己的操作
	        System.out.println("after method send()");
	        return null;
	    }
	}
	
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");
    }
}
