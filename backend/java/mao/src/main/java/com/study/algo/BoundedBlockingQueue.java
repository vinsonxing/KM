package com.study.algo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue {
    private int[] data;
    private int head, tail, size;
    private final ReentrantLock lock;
    private Condition canOffer;
    private Condition canPoll;

    public BoundedBlockingQueue(int capacity) {
        data = new int[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
        lock = new ReentrantLock();
        canOffer = lock.newCondition();
        canPoll = lock.newCondition();
    }

    private int next(int x) {
        return (x + 1) % size;
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try {
            while (next(tail) == head) {
                canOffer.await();
            }
            data[tail] = element;
            tail = next(tail);
            canPoll.signal();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        try{
           while(tail == head) {
               canPoll.await();
           }
           int res = data[tail];
           tail = next(tail);
           canOffer.signal();
           return res;
        }finally {
            lock.unlock();
        }
    }
}
