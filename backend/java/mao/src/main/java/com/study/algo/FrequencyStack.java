package com.study.algo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class FrequencyStack {
    private Map<Integer, Integer> cnt = new HashMap<>();
    private Map<Integer, Deque<Integer>> d = new HashMap<>(); //同样次数有先后
    private int mx;

    public void push(int val) {
        cnt.put(val, cnt.getOrDefault(val, 0) + 1);
        int t = cnt.get(val);
        d.computeIfAbsent(t, k -> new ArrayDeque<>()).push(val);
        mx = Math.max(mx, t);
    }

    public int pop() {
        int val = d.get(mx).pop();
        cnt.put(val, cnt.get(val) - 1);
        if (d.get(mx).isEmpty()) {
            mx--;
        }
        return val;
    }

    public static void main(String[] args) {

    }

}
