package com.vinson.base.concurrent;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {
	
	public static void main(String[] args) {
		CompletableFuture.completedFuture("hi, ").thenApply(s -> s + "Vinson").thenAcceptAsync(System.out::println);
		System.out.println("Print before above message");
		
		
		CompletableFuture.completedFuture("hi, ").thenApply(s -> s + "Vinson").thenAccept(System.out::println);
		System.out.println("Print after above message");
	}

}
