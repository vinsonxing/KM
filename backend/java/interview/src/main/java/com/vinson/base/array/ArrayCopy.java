package com.vinson.base.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayCopy {

    private static class WeightedSample {
        public final long value;
        public final double weight;

        public WeightedSample(long value, double weight) {
            this.value = value;
            this.weight = weight;
        }
    }
    
    public static void main(String[] args) {
        WeightedSample ws1 = new WeightedSample(111111111, 10.1);
        WeightedSample ws2 = new WeightedSample(222222222, 1.1);
        WeightedSample ws3 = new WeightedSample(333333333, 8.1);
        WeightedSample[] ws = new WeightedSample[] {ws2, ws3, ws1};
        
        for (WeightedSample w : ws) {
            System.out.println(w.value);
        }
        System.out.println("---------------------" + ThreadLocalRandom.current().nextDouble());
        Arrays.sort(ws, Comparator.comparingLong(w -> w.value));
        
        for (WeightedSample w : ws) {
            System.out.println(w.value);
        }
    }
}
