package com.vinson.study.queue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DelayQueueTest {
    static final int STUDENT_SIZE = 5;

    public static void main(String[] args) throws InterruptedException {
        Random r = new Random();
        // 把所有学生看做一个延迟队列
        DelayQueue<DelayQueueStudent> students = new DelayQueue<DelayQueueStudent>();
        // 构造一个线程池用来让学生们“做作业”
        ExecutorService exec = Executors.newFixedThreadPool(STUDENT_SIZE);
        for (int i = 0; i < STUDENT_SIZE; i++) {
            // 初始化学生的姓名和做题时间
            students.put(new DelayQueueStudent("学生" + (i + 1), 3000 + r.nextInt(10000)));
        }

        students.stream().forEach(s -> s.setDQ(students));
        System.out.println(students);
        // 开始做题
        while (!students.isEmpty()) {
            // 每次从DeplayQueue中取出一个元素，剩下的元素会再次排序，因为DeplayQueue内部依赖PriorityQueue来存储元素。-- vinson
            exec.execute(students.take());
        }
        exec.shutdown();
    }
}
