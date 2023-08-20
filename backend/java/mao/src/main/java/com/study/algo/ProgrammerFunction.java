package com.study.algo;

import java.util.HashMap;
import java.util.Map;

public class ProgrammerFunction {
    static int getMidIndexCount(String s) {
        String p = "programmer";
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int left = 0, right = s.length() - 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);

                if (map.get(c) == 0) {
                    map.remove(c);
                }
                if (map.isEmpty()) {
                    left = i;
                    break;
                }
            }
        }
        for (int i = 0; i < p.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int j = s.length() - 1; j >= 0; j--) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);

                if (map.get(c) == 0) {
                    map.remove(c);
                }
                if (map.isEmpty()) {
                    right = j;
                    break;
                }
            }
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        String s = "programmerxxxprozmerqgram";
        System.out.println(getMidIndexCount(s));
    }
}
