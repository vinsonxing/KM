package com.vinson.base.thread.executor;

public class WorkThread implements Runnable{

	private String wName;
	public WorkThread(String n) {
		// TODO Auto-generated constructor stub
		wName = n;
	}
	
	public void run() {
		System.out.println(wName);
		try
    {
      Thread.sleep(500);
    }
    catch (InterruptedException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	}
}
