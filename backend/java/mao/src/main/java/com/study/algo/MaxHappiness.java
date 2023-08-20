package com.study.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class MaxHappiness {
    // a 是愉悦 b 是财富
    private static int lessGap(int[] a, int[] b, long k) {
        int n = a.length;
        int[][] f = new int[n][2];
        for (int i = 0; i < n; i++) {
            f[i][0] = a[i];
            f[i][1] = b[i];
        }
        Arrays.sort(f, (x, y) -> x[1] - y[1]);

        TreeSet ts = new TreeSet();
        for (int i = 0; i < f.length; i++) {
            for (int j = i; j < f.length; j++) {
                ts.add(f[j][1] - f[i][1]);
            }
        }
        List<Integer> diff = new ArrayList(ts);
        int l = 0;
        int r = diff.size();
        int m = 0;
        int ans = -1;
        while (l < r) {
            m = (l + r) / 2;
            long mh = maxHappy(f, diff.get(m));
            if (mh < k) {
                l = m + 1;
            } else {
                ans = Math.min(ans, diff.get(m));
                r = m;
            }
        }
        return ans;
    }

    private static long maxHappy(int[][] f, int limit) {
        int n = f.length;
        long sum = 0;
        long ans = 0;
        for (int l = 0, r = 0; l < n; l++) {
            while (r < n && f[r][1] - f[l][1] <= limit) {
                sum += f[r++][0];
            }
            ans = Math.max(sum, ans);
            sum -= f[l][0];
            //r = Math.max(r, l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 4, 5, 3, 15}; //愉悦
        int[] b = new int[]{1, 2, 3, 10, 12}; //财富
        int k = 20;
        System.out.println(lessGap(a, b, k));
    }

    /*
    第一步 从问题推条件的单调性 本题就是在隔阂变大愉悦变大
    第二步 推出问题的范围
    第三步 写功能函数判断是否达标 （标准是条件）
    第四步 二分
     */
}
