package com.study.algo;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.ca/2016-02-14-76-Minimum-Window-Substring/
//主要考虑滑动窗口的收缩问题 AAAABC
public class MinimumWindowSubstring {
    public static String minWindow1(String s, String t) {
        int size = s.length();
        int counter = t.length();
        Map<Character, Integer> source = new HashMap<>();
        Map<Character, Integer> target = new HashMap<>();
        char[] tarr = t.toCharArray();
        char[] sarr = s.toCharArray();
        for (char c : tarr) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0, minStart = 0, minEnd = 0, min = s.length();
        while (right < size) {
            Character rightC = sarr[right];
            if (target.containsKey(rightC)) {
                target.put(rightC, target.get(rightC) - 1);
                counter--;
            }
            //滑动窗口收缩问题，因为符合条件在while循环里，所以在left右移的过程中子数组应该捕获
            while (counter == 0) {
                if (min > right - left + 1) {
                    min = right - left + 1;
                    minStart = left;
                    minEnd = right;
                }
                Character leftC = sarr[left];
                if (target.containsKey(leftC)) {
                    target.put(leftC, target.get(leftC) + 1);
                    if (target.get(leftC) > 0) {
                        counter++;
                    }
                }
                left++;
            }
            right++;
        }
        return s.substring(minStart, minEnd + 1);
    }

    public static String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        int begin = 0, end = 0, minLen = Integer.MAX_VALUE, size = s.length(), minStart = 0;
        int counter = t.length();
        for (char c : s.toCharArray()) {
            map.put(c, 0);
        }
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        while (end < size) {
            if (map.get(s.charAt(end)) > 0) {
                counter--;
            }
            map.put(s.charAt(end), map.get(s.charAt(end)) - 1);
            while (counter == 0) {
                if (end - begin + 1 < minLen) {
                    minStart = begin;
                    minLen = end - begin + 1;
                }
                map.put(s.charAt(begin), map.get(s.charAt(begin)) + 1);
                if (map.get(s.charAt(begin)) > 0) {
                    counter++;
                }
                begin++;
            }
            end++;
        }
        if (minLen != Integer.MAX_VALUE) {
            return s.substring(minStart, minStart + minLen);
        }
        return "";
    }

    public static void main(String[] args) {
        String s = "DADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
        System.out.println(minWindow1(s, t));
    }
}
