package com.vinson.study.datastracture.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueStudent implements Runnable, Delayed {
    private String name; // 姓名
    private long costTime;// 做试题的时间
    private long finishedTime;// 完成时间
    private DelayQueue dq;

    public DelayQueueStudent(String name, long costTime) {
        this.name = name;
        this.costTime = costTime;
        finishedTime = costTime + System.currentTimeMillis();
    }

    public void setDQ(DelayQueue dq) {
        this.dq = dq;
    }

    @Override
    public void run() {
        System.out.println(name + " 交卷,用时" + costTime / 1000);
        System.out.println(dq);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long sd = (finishedTime - System.currentTimeMillis());
        return sd;
    }

    @Override
    public int compareTo(Delayed o) {
        DelayQueueStudent other = (DelayQueueStudent) o;
        return costTime >= other.costTime ? 1 : -1;
    }

    @Override
    public String toString() {
        return "学生：" + this.name;
    }
}
