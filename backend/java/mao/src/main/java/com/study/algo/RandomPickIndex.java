package com.study.algo;

import java.util.Random;

//https://leetcode.ca/2017-01-01-398-Random-Pick-Index/
public class RandomPickIndex {
    int[] nums;
    Random rnd;
    public int pick(int target) {

        int resultIndex = -1;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) {
                continue;
            }

            count++;

            if (rnd.nextInt(count) == 0) {
                resultIndex = i;
                // first time nextInt is always 0, so set as first index
                // for the rest, probability issue
            }
        }

        return resultIndex;
    }
}
