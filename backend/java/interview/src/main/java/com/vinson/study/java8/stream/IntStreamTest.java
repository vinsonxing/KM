package com.vinson.study.java8.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreamTest {

    public static void main(String[] args) {

        Stream<int[]> tt = IntStream.rangeClosed(1,  100).boxed().flatMap(a ->
            IntStream.rangeClosed(a, 100)
            .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
            .mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b)})
        );

        tt.limit(5).forEach(t -> {
            System.out.println(t[0] + "," + t[1] + "," + t[2]);
        });
    }
}
