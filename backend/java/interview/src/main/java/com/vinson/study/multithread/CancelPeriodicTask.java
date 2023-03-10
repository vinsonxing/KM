package com.vinson.study.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CancelPeriodicTask {

    public static void main(String[] args) {

        ScheduledThreadPoolExecutor scheduler = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        scheduler.setRemoveOnCancelPolicy(true);
        try {
            new CancelPeriodicTask().test(scheduler);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            int openTasks = scheduler.shutdownNow().size();
            println("Finished, open tasks: " + openTasks);
            // openTasks will be 1 when RemoveOnCancelPolicy is false
            // and the executor is closed within the scheduled task-period.
        }
    }

    private static long sleepTime = 500L;

    public void test(ScheduledThreadPoolExecutor scheduler) throws Exception {

        // sleep 5 times at scheduled interval
        SleepTask sleepTask = new SleepTask();
        ScheduledFuture<?> scheduledSleep = scheduler.scheduleAtFixedRate(sleepTask, 0, 2 * sleepTime, TimeUnit.MILLISECONDS);
        sleepTask.sleepTimes.await();
        scheduledSleep.cancel(false);
        println("Cancelling scheduledSleep. Done: " + scheduledSleep.isDone() + ", cancelled: " + scheduledSleep.isCancelled());
        Thread.sleep(10 * sleepTime);
       
//        println("Running sleepTask once.");
//        scheduler.submit(sleepTask);
//        Thread.sleep(2 * sleepTime);
//        scheduledSleep = scheduler.scheduleAtFixedRate(sleepTask, 0, 2 * sleepTime, TimeUnit.MILLISECONDS);
//        println("Re-scheduled scheduledSleep. Done: " + scheduledSleep.isDone() + ", cancelled: " + scheduledSleep.isCancelled());
//        Thread.sleep(5 * sleepTime);
//        println("Cancelling scheduledSleep. Done: " + scheduledSleep.isDone() + ", cancelled: " + scheduledSleep.isCancelled());
//        scheduledSleep.cancel(true);
    }

    class SleepTask implements Runnable {

        public final CountDownLatch sleepTimes = new CountDownLatch(5); 
        public int sleepCount;

        @Override public void run() {
            println("Sleeping " + (++sleepCount));
			try {
				Thread.sleep(sleepTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
            sleepTimes.countDown();
        }
    }

    private static final long START_TIME = System.currentTimeMillis(); 

    private static void println(String msg) {
        System.out.println((System.currentTimeMillis() - START_TIME) + "\t " + msg);
    }

}