package com.study.algo;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.ca/2018-10-27-1062-Longest-Repeating-Substring/
//https://blog.csdn.net/olivia_yuen/article/details/125913135
//很不容易想到二分，极值勿忘二分，想想解空间范围
public class LongestRepeatingSubstring {
    static boolean isRepeating(String s, int len) {
        Set set = new HashSet();
        for (int i = 0; i <= s.length() - len; i++) {
            String sub = s.substring(i, i + len);
            if (set.contains(sub)) {
                return true;
            }
            set.add(sub);
        }
        return false;
    }

    static int longestRepeatingSubstring(String s) {
        int n = s.length();
        int l = 0, r = n;
        int ans = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (isRepeating(s, mid)) {
                l = mid + 1;
                ans = Math.max(ans, mid);
            } else {
                r = mid;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcefbcef";
        System.out.println(longestRepeatingSubstring(s));
    }
}
