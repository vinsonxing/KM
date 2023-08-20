package com.study.algo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//https://www.bilibili.com/video/BV1SL411B71a?p=90
public class ReplaceSubstringForBalancedString {
    /*
    substring -> 连续说明是窗口问题
    词频统计
    滑动窗口内是可以任意调整的
     */
    /*
    除了递归和dp，滑动窗口可以实现最终全局目标而获得最佳substring

     */
    public static int balancedString(String s) {
        int[] cnt = new int[4];
        String t = "QWER";
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            cnt[t.indexOf(s.charAt(i))]++;
        }
        int m = n / 4;
        if (cnt[0] == m && cnt[1] == m && cnt[2] == m && cnt[3] == m) {
            return 0;
        }
        int ans = n;
        for (int i = 0, j = 0; i < n; ++i) {
            cnt[t.indexOf(s.charAt(i))]--;
            while (j <= i && cnt[0] <= m && cnt[1] <= m && cnt[2] <= m && cnt[3] <= m) {
                ans = Math.min(ans, i - j + 1);
                cnt[t.indexOf(s.charAt(j++))]++;
            }
        }
        return ans;
    }

    public static int balancedString1(String s) {
        int n = s.length();
        int[] arr = new int[n];
        int[] cnts = new int[4];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            arr[i] = c == 'W' ? 1 : (c == 'E' ? 2 : (c == 'R' ? 3 : 0));
            cnts[arr[i]]++;
        }
        int ans = n;
        for (int l = 0, r = 0; l < n; l++) {
            while (!ok(cnts, l, r) && r < n) {
                cnts[arr[r++]]--;
            }
            if (ok(cnts, l, r)) {
                ans = Math.min(ans, r - l);
            } else {
                break;
            }
            cnts[arr[l++]]++;
        }
        return ans;
    }
    //这道题的滑动窗口是
    public static int balancedString2(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int ans = n;
        int l = 0, r = 0;
        while (l < n) {
            //不符合的原因是因为包含的不够，只有扩大窗口才有可能性再次符合
            while (!ok1(map, l, r) && r < n) {
                map.put(s.charAt(r), map.get(s.charAt(r)) - 1);
                r++;
            }
            if (ok1(map, l, r)) {
                ans = Math.min(ans, r - l);
            } else {
                break;
            }
            map.put(s.charAt(l), map.get(s.charAt(l)) + 1);
            l++;
        }
        return ans;
    }

    private static boolean ok(int[] cnts, int l, int r) {
        int maxCnt = Math.max(Math.max(cnts[0], cnts[1]), Math.max(cnts[2], cnts[3]));
        int changes = maxCnt * 4 - cnts[0] - cnts[1] - cnts[2] - cnts[3];
        int rest = r - l - changes;
        return rest >= 0 && rest % 4 == 0;
    }

    private static boolean ok1(Map<Character, Integer> cnts, int l, int r) {
        int maxCnt = cnts.values().stream().max(Comparator.comparingInt(v -> v)).get();
        int sumCnt = cnts.values().stream().mapToInt(v -> v).sum();
        int changes = maxCnt * 4 - sumCnt;
        int rest = r - l - changes;
        return rest >= 0 && rest % 4 == 0;
    }

    public static void main(String[] args) {
        String s = "QQQWEQERQEQW";
        System.out.println(balancedString(s));
        System.out.println(balancedString1(s));
        System.out.println(balancedString2(s));
    }
}
