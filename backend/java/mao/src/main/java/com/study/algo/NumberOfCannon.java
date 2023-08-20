package com.study.algo;

import java.util.TreeMap;

public class NumberOfCannon {
    public static int numOfCannon(int[] arr) {
        TreeMap<Integer, Integer> cannonMap = new TreeMap<>();
        for (int num : arr) {
            if (cannonMap.ceilingKey(num + 1) == null) {
                cannonMap.put(Integer.MAX_VALUE, 1);
            }
            int ceilKey = cannonMap.ceilingKey(num + 1);
            if (cannonMap.get(ceilKey) > 1) {
                cannonMap.put(ceilKey, cannonMap.get(ceilKey) - 1);
            } else {
                cannonMap.remove(ceilKey);
            }
            cannonMap.put(num, cannonMap.getOrDefault(num, 0) + 1);

        }
        int ans = 0;
        for (int value : cannonMap.values()) {
            ans += value;
        }
        return ans;
    }
}
