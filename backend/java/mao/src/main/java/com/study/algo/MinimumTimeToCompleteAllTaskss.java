package com.study.algo;
//https://leetcode.ca/2023-04-26-2589-Minimum-Time-to-Complete-All-Tasks/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimumTimeToCompleteAllTaskss {
    public static void main(String[] args) {
        int[][] tasks = {{1,3,2}, {2, 5, 3}, {5, 6, 2}};
        System.out.println(getMinimumTime(tasks));
    }

    static int getMinimumTime(int[][] tasks) {
        boolean[] used = new boolean[2001];
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int ans = 0;
        for (int[] task : tasks) {
            int count = task[2];
            for (int index = task[0]; index <= task[1]; index++) {
                if (used[index]) {
                    count--;
                }
            }
            if (count <= 0) {
                continue;
            }
            ans += count;
            //贪心
            for (int index = task[1]; index >= task[0]; index--) {
                if (used[index]) {
                    continue;
                }
                used[index] = true;
                if (--count == 0) {
                    break;
                }
            }
        }
        return ans;
    }
}
