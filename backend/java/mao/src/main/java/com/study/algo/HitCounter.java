package com.study.algo;

import java.util.ArrayDeque;

//https://leetcode.ca/2016-11-26-362-Design-Hit-Counter/
public class HitCounter {
    ArrayDeque<Integer> deque;
    public HitCounter(){
        deque = new ArrayDeque<>();
    }
    public void hit(int timestamp){
        deque.offer(timestamp);
    }
    public int getHits(int timestamp){
        int startTime = timestamp - 300;
        while (!deque.isEmpty() && deque.peek() <= startTime){
            deque.poll();
        }
        return deque.size();
    }

}
