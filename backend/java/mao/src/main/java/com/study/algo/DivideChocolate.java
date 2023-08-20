package com.study.algo;

import java.util.Arrays;

public class DivideChocolate {
    static int getGroups(int[] chunks, int limit) {
        int sum = 0;
        int group = 0;
        //最小是limit，每个group 要大于limit
        for (int chunk : chunks) {
            sum += chunk;
            if (sum >= limit) {
                group++;
                sum = 0;
            }
        }
        return group;
    }

    static int getMySweetness(int[] chunks, int k) {
        int min = Arrays.stream(chunks).min().getAsInt();
        int max = Arrays.stream(chunks).sum() / k + 1;
        int mySweetness = 0;
        int left = min, right = max;
        while (left < right) {
            int mid = (left + right) / 2;
            int groups = getGroups(chunks, mid);
            if (groups >= k + 1) {
                mySweetness = Math.max(mySweetness, mid);
                left = mid + 1;
            } else {
                right = mid;
            }
        }
//        return left - ;//注意你的最佳值结果区间（包含等号）是left = mid + 1, 所以最终结果必然是left - 1或者right -1 (因为left=right)
        return mySweetness;
    }

    public static void main(String[] args) {
        int[] chocalate = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(getMySweetness(chocalate, 5));
    }
}
