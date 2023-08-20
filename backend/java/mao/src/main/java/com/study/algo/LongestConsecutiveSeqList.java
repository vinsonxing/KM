package com.study.algo;
//https://leetcode.ca/2016-04-06-128-Longest-Consecutive-Sequence/
import java.util.HashSet;
import java.util.Set;
//必须想到set
public class LongestConsecutiveSeqList {
    //顺序无关-》SET存储
    public int longestConsecutive(int[] nums) {
        Set<Integer> hs = new HashSet<Integer>();
        for (int num : nums) {
            hs.add(num);
        }
        int longestStreak = 0;

        for (int num : hs) {
            if (!hs.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (hs.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
