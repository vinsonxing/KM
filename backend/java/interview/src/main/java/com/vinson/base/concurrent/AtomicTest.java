package com.vinson.base.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicTest {

    public static void main(String[] args) {
        testVarOutside();
    }
    
    private static void testVarOutside() {
        final AtomicInteger count = new AtomicInteger(0);
        IntStream.range(1, 10).forEach(i -> {
            count.getAndIncrement();
            System.out.println(count);
        });
    }
}
