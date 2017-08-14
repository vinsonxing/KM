package com.vinson.study.java8.stream;

import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
//        Stream.iterate(new int[]{0,1}, (a) -> new int[] {a[0] + a[1], a[1] + a[0] + a[1]}).limit(20).forEach(b -> {
//            System.out.println("(" + b[0] + "," + b[1] + ")");
//        });
    }

    static void fibonacciGenerator() {
        Stream.iterate(new int[]{0,1}, (a) -> new int[] {a[1], a[0] + a[1]}).limit(20).map(r->r[0]).forEach(System.out::println);
    }
    static void simpleIterator() {
        Stream.iterate(0, n->n+2).limit(10).forEach(System.out::println);
    }

}
