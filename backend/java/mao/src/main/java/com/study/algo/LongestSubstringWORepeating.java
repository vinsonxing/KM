package com.study.algo;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWORepeating {
    static String longestWORepeat(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, maxLeft = 0, maxRight = 0, len = s.length();
        int max = 0;
        while (right < len) {
            if (!set.contains(s.charAt(right))) {
                if (max < right - left + 1) {
                    max = right - left + 1;
                    maxLeft = left;
                    maxRight = right;
                }
            }
            //不符合的原因是已经有重复的进入窗口，只能从左边扔出去，因为你再往右重复的字符也还存在
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            right++;
        }
        return s.substring(maxLeft, maxRight + 1);
    }

    public static void main(String[] args) {
        System.out.println(longestWORepeat("ecaebbcdaf"));
    }
}
