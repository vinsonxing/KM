package com.study.algo;

public class InternTaskCompletion {
    static int getMaximumRewardPoints(int[] reward1, int[] reward2, int index, int k, int len1) {
        if (index == reward1.length) {
            return 0;
        }
        int p1 = 0, p2 = 0;
        if (len1 == k) {
            p2 = reward2[index] + getMaximumRewardPoints(reward1, reward2, index + 1, k, len1);
        } else if (len1 < k) {
            p1 = reward1[index] + getMaximumRewardPoints(reward1, reward2, index + 1, k, len1 + 1);
            p2 = reward2[index] + getMaximumRewardPoints(reward1, reward2, index + 1, k, len1);
        }
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
        int[] reward1 = {1, 2, 3, 2};
        int[] reward2 = {1, 2, 3, 2};
        System.out.println(getMaximumRewardPoints(reward1, reward2, 0, 3, 0));
    }
}
