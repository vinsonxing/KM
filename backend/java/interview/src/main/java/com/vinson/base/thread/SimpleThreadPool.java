package com.vinson.base.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimpleThreadPool {
	private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 50; // if queue is full, the new threads will be created until MAX_POOL_SIZE
    private static final Long KEEP_ALIVE_TIME = 1L;
    
	public static void main(String[] args) {
//		ThreadPoolExecutor executor = new ThreadPoolExecutor(
//                CORE_POOL_SIZE,
//                MAX_POOL_SIZE,
//                KEEP_ALIVE_TIME,
//                TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
//                new ThreadPoolExecutor.CallerRunsPolicy());
//        for (int i = 0; i < 100; i++) {
//            Runnable worker = new WorkThread("" + i);
//            executor.execute(worker);
//          }
//        executor.shutdown();// Vinson: Must invoke the shutdown AFTER the execute(), otherwise the JVM won't shutdown.
//        System.out.println("shut the executor");
//        while (!executor.isTerminated()) {
//        }
//        System.out.println("Finished all threads");
//        
        List<String> rr = new LinkedList<>();
        
        System.out.println(1.0 == 1.0000000000000000000000001);
        System.out.println(1.0 == 0.999999999999999999999);
        System.out.println(Float.compare(1.0f, 0.99999999999999999999f));
 	}
	
	private static class WorkThread implements Runnable {
		private String wName;

		public WorkThread(String n) {
			// TODO Auto-generated constructor stub
			wName = n;
			
		}

		public void run() {
			System.out.println(Thread.currentThread().getName() + "-" + wName);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
