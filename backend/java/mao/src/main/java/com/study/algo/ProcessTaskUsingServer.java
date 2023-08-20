package com.study.algo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//https://leetcode.ca/2021-07-22-1882-Process-Tasks-Using-Servers/
public class ProcessTaskUsingServer {
    public static void main(String[] args) {
        int[] servers = {5, 1, 4, 3, 2};
        int[] tasks = {2, 1, 2, 4, 5, 2, 1};
        ProcessTaskUsingServer solution = new ProcessTaskUsingServer();
        System.out.println(Arrays.toString(solution.getSeverList(servers, tasks)));
    }

    int[] getSeverList(int[] servers, int[] tasks) {
        int serverNum = servers.length;
        int taskNum = tasks.length;
        int[] ans = new int[taskNum];
        int index = 0;
        PriorityQueue<Server> busypq = new PriorityQueue<>(new Comparator<Server>() {
            @Override
            public int compare(Server o1, Server o2) {
                if (o1.availableTime != o2.availableTime) {
                    return o1.availableTime - o2.availableTime;
                } else if (o1.weight != o2.weight) {
                    return o1.weight - o2.weight;
                } else {
                    return o1.id - o2.id;
                }
            }
        });
        PriorityQueue<Server> idlepq = new PriorityQueue<>(new Comparator<Server>() {
            @Override
            public int compare(Server o1, Server o2) {
                if (o1.weight != o2.weight) {
                    return o1.weight - o2.weight;
                } else {
                    return o1.id - o2.id;
                }
            }
        });
        for (int i = 0; i < serverNum; i++) {
            idlepq.offer(new Server(i, servers[i], 0));
        }
        for (int tid = 0; tid < taskNum; tid++) {
            while (!busypq.isEmpty() && busypq.peek().availableTime <= tid) {
                idlepq.offer(busypq.poll());
            }
            if (idlepq.isEmpty()) {
                Server server = busypq.poll();
                ans[index++] = server.id;
                server.availableTime = server.availableTime + tasks[tid];
                busypq.offer(server);
            } else {
                Server server = idlepq.poll();
                ans[index++] = server.id;
                server.availableTime = tid + tasks[tid];
                busypq.offer(server);
            }
        }
        return ans;
    }
}

class Server {
    int id;
    int weight;
    int availableTime;

    public Server(int id, int weight, int availableTime) {
        this.id = id;
        this.weight = weight;
        this.availableTime = availableTime;
    }
}
