package com.study.algo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithAtMost2DistinctCharacter {
    public static void main(String[] args) {
        String input = "ececabbaaaad";
        System.out.println(lengthOfLongestSubstringTwoDistinct(input));
    }

    static String lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0, maxLeft = 0, maxRight = 0, len = s.length();
        int max = 0;
        while (right < len) {
            map.put(s.charAt(right), right);
            if (map.size() <= 2) {
                if (max < right - left + 1) {
                    max = right - left + 1;
                    maxLeft = left;
                    maxRight = right;
                }
            }
            while (map.size() > 2) {
                int temp = left;
                left = map.get(s.charAt(left)) + 1;
                map.remove(s.charAt(temp));
            }
            right++;
        }
        return s.substring(maxLeft, maxRight + 1);
    }
}
