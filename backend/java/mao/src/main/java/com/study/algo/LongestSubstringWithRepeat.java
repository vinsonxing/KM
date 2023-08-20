package com.study.algo;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithRepeat {
    public static String longestSubstring(String str, int k) {
        int left = 0, right = 0, max = 0;
        int len = str.length();
        int maxStart = 0, maxEnd = 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        Map<Character, Integer> currMap = new HashMap<>();
        for (Character ch : str.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        while (right < len) {
            Character ch = str.charAt(right);
            if (freqMap.get(ch) >= k) {
                currMap.put(ch, currMap.getOrDefault(ch, 0) + 1);
                Integer min = currMap.values().stream().min(Comparator.comparing(a -> a)).orElse(null);
                if (min >= k && right - left + 1 > max) {
                    maxStart = left;
                    maxEnd = right;
                    max = right - left + 1;
                }
            } else {
                currMap.clear();
                left = right + 1;
            }
            right++;
        }
        return str.substring(maxStart, maxEnd + 1);
    }

    public static void main(String[] args) {
        String str = "ababbccbc";
        System.out.println(longestSubstring(str, 3));

    }
}
