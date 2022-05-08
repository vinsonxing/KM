package com.vinson.base.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            Runnable worker = new WorkThread("" + i);
            executor.execute(worker);
          }
        executor.shutdown();// Vinson: Must invoke the shutdown AFTER the execute(), otherwise the JVM won't shutdown.
        System.out.println("shut the executor");
        /*while (!executor.isTerminated()) {
        }*/
        System.out.println("Finished all threads");
        System.out.println(int.class);
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
