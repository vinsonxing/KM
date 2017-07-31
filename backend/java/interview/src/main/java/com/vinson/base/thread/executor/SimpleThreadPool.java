package com.vinson.base.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkThread("" + i);
            executor.execute(worker);
          }
        executor.shutdown();// Vinson: Must invoke the shutdown AFTER the execute(), otherwise the JVM won't shutdown.
        System.out.println("shut the executor");
        /*while (!executor.isTerminated()) {
        }*/
        System.out.println("Finished all threads");
	}

}
