package com.vinson.study.java8.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompletableFutureTest {
	private static final Logger logger = LoggerFactory.getLogger(CompletableFutureTest.class);
	
	public static void main(String[] args) throws Exception {
//		simpleFuture();
//		completedFuture();
//		computationFuture();
//		asynChainFuture();
//		asynChainFuture1();
//		asynChainFuture2();
//		composedFuture();
//		combinedFuture();
//		parallelFuture();
//		parallelFuture1();
//	    completedFutureWhen();
//	    computationFutureWhen1();
	    workAsPromise(2000l); // no error
	    System.out.println("====================");
	    workAsPromise(5000l); // has error
	}
	
	private static void simpleFuture() throws InterruptedException, ExecutionException {
		CompletableFuture<String> cf = new CompletableFuture<>();
		
		Executors.newCachedThreadPool().submit(() -> {
			try {
				Thread.sleep(1000l);
			} catch (InterruptedException e) {
				CompletableFuture.failedFuture(e);
				e.printStackTrace();
			}
			cf.complete("Hello simpleFuture");
		});
		
		logger.info("Before get");
		String str = cf.get();
		logger.info(str);
	}
	
	
	private static void completedFuture() throws InterruptedException, ExecutionException {
		CompletableFuture<String> cf = CompletableFuture.completedFuture("Hello completedFuture");
		logger.info(cf.get());
	}
	
	private static void completedFutureWhen() throws InterruptedException, ExecutionException {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("Hello completedFuture");
        cf.whenComplete((r, t) -> {
            logger.info(r);
        });
        logger.info("Done");
    }
	
	private static void computationFuture() throws InterruptedException, ExecutionException {
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
			logger.info("Tread for computationFuture");
			return "Hello computationFuture";
		});
		logger.info(cf.get());
	}
	
	private static void computationFutureWhen1() throws InterruptedException, ExecutionException {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            logger.info("Tread for computationFuture");
            return "Hello computationFuture";
        });
        cf.whenComplete((r, t) -> {
            logger.info(r);
        });
        logger.info("Done");
    }
	
	private static void asynChainFuture() throws InterruptedException, ExecutionException {
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
			logger.info("Tread for asynChainFuture-Hello");
			return "Hello";
		});
		// Function interface, has return
		CompletableFuture<String> cf1 = cf.thenApply(s -> {
			logger.info("Tread for asynChainFuture-world");
			return s + " world";
		});
		logger.info(cf1.get());
	}
	
	private static void asynChainFuture1() throws InterruptedException, ExecutionException {
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
			logger.info("Tread for asynChainFuture1");
			return "Hello";
		});
		// Consumer interface, no return, so Void
		CompletableFuture<Void> cf1 = cf.thenAccept(s -> System.out.println(s + " world accept"));
		cf1.get();
	}
	
	private static void asynChainFuture2() throws InterruptedException, ExecutionException {
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
			logger.info("Current Thread => {}", Thread.currentThread().getName());
			return "Hello ";
		});
		// Runnable, no return, no passin
		CompletableFuture<Void> cf1 = cf.thenRun(() -> {
			logger.info("Current Thread => {}", Thread.currentThread().getName());
			logger.info("Runnable is executed");
		});
		cf1.get();
	}
	
	private static void composedFuture() throws InterruptedException, ExecutionException {
		CompletableFuture<String> cf = CompletableFuture
				.supplyAsync(() -> "Hello")
				.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
		
		System.out.println(cf.get());
	}
	
	private static void combinedFuture() throws InterruptedException, ExecutionException {
		CompletableFuture<String> cf = CompletableFuture
				.supplyAsync(() -> "Hello")
				.thenCombine(CompletableFuture.supplyAsync(() -> "World"), (a, b) -> a + "-" + b);
		System.out.println(cf.get());
	}
	
	private static void parallelFuture() throws InterruptedException, ExecutionException {
		CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(1000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Beautiful";
		});
		CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "World");
		
		CompletableFuture<Void> combined = CompletableFuture.allOf(f1, f2, f3);
		combined.get();
		System.out.println(f1.isDone() + f1.get());
		System.out.println(f2.isDone() + f2.get());
		System.out.println(f3.isDone() + f3.get());
	}
	
	private static void parallelFuture1() throws InterruptedException, ExecutionException {
		CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
			logger.info("Hello");
			return "Hello";
		});
		CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
			logger.info("Beautiful");
			try {
				Thread.sleep(1000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Beautiful";
		});
		CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> {
			logger.info("World");
			return  "World";
		});
		
//		f1.join();
//		f2.join();
//		f3.join();
//		System.out.println("Done");
		
		String ret = Stream.of(f1, f2, f3)
			  .map(CompletableFuture::join)
			  .collect(Collectors.joining(" "));
		System.out.println(ret);
	}
	
	private static void workAsPromise(long time) throws Exception {
	    CompletableFuture<String> f1 = new CompletableFuture<>();
	    f1.whenComplete((s, t) -> {
	        if (t != null) {
	            System.out.println("got some error::: " + t.getMessage());
	            return;
	        }
	        System.out.println("Hello " + s);
	    });
	    System.out.println("waiting....");
	    // run task
	    someTaskNeedTime(f1, time);
	}
	
	private static void someTaskNeedTime(CompletableFuture<String> future, long threshold) throws Exception {
	    long waitingTime = 3000l;
	    Thread.sleep(3000l);
	    if (threshold > waitingTime) {
	        future.completeExceptionally(new Exception("too loooong !"));
	        return;
	    }
        future.complete("maomao");
	}
}
