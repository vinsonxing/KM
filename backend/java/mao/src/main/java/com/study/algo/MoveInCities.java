package com.study.algo;
//找踩谁最优
/*
还是遍历city list最终到哪个城市代价最小
 */
import java.util.Arrays;

public class MoveInCities {
    public static int maxPorfit1(int n, int m, int k, int[] c, int[] a, int[] b) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }
        return process1(k, 0, m, c, a, b, dp);
    }
    //暴力递归
    public static int zuo(int currCity, int i, int[] c, int[] a, int[] b) {
        if(i == c.length) {
            return 0;
        }
        int p1 = zuo(currCity, i+1, c, a, b);
        int toCity = c[i];
        int p2 = currCity == toCity ? a[i]:b[i];
        p2 += zuo(toCity, i+1, c, a, b);
        return Math.max(p1, p2);
    }

    // cur : 小美当前在哪！
    // i : 当前面临的是任务编号！
    // m : 一共有多少任务，固定
    // c[i] : 第i号任务要在哪个城里完成
    // a[i] : 恰好在！收益
    // b[i] : 赶过去！收益
    // 返回 : i....... 小美获得的最大收益
    public static int process1(int cur, int i, int m, int[] c, int[] a, int[] b, int[][] dp) {
        if (i == m) {
            return 0;
        }
        if (dp[cur][i] != -1) {
            return dp[cur][i];
        }
        // 可能性1 : 不做任务，彻底放弃，留在原地
        int p1 = process1(cur, i + 1, m, c, a, b, dp);
        // 可能性2 : 做任务，要看看cur在哪，来获得收益
        int p2 = 0;
        if (cur == c[i]) {
            p2 = a[i] + process1(c[i], i + 1, m, c, a, b, dp);
        } else {
            p2 = b[i] + process1(c[i], i + 1, m, c, a, b, dp);
        }
        int ans = Math.max(p1, p2);
        dp[cur][i] = ans;
        return ans;
    }

    // 正式方法
    // 时间复杂度O(N*logN)
    public static int maxPorfit2(int n, int m, int k, int[] c, int[] a, int[] b) {
        SegmentTree st = new SegmentTree(n);
        st.update(k, 0);
        int ans = 0;
        for (int i = 0; i < m; i++) {
            // c[i]
            int curAns = Math.max(Math.max(st.max(0, c[i] - 1), st.max(c[i] + 1, n - 1)) + b[i],
                    st.max(c[i], c[i]) + a[i]);
            ans = Math.max(ans, curAns);
            st.update(c[i], curAns);
        }
        return ans;
    }

    public static class SegmentTree {
        private int n;
        private int[] max;

        public SegmentTree(int N) {
            n = N;
            max = new int[(n + 1) << 2];
            Arrays.fill(max, Integer.MIN_VALUE);
        }

        public int max(int l, int r) {
            l++;
            r++;
            if (l > r) {
                return Integer.MIN_VALUE;
            }
            return max(l, r, 1, n, 1);
        }

        public void update(int i, int v) {
            i++;
            update(i, i, v, 1, n, 1);
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                max[rt] = Math.max(max[rt], C);
                return;
            }
            int mid = (l + r) >> 1;
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        private int max(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            int left = Integer.MIN_VALUE;
            int right = Integer.MIN_VALUE;
            if (L <= mid) {
                left = max(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = max(L, R, mid + 1, r, rt << 1 | 1);
            }
            return Math.max(left, right);
        }

    }

    // 为了测试
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v);
        }
        return ans;
    }

    // 为了测试
    public static void main(String[] args) {

//        int N = 10;
//        int M = 10;
//        int V = 5;
//        int testTimes = 5000;
//        System.out.println("功能测试开始");
//        for (int i = 0; i < testTimes; i++) {
//            int n = (int) (Math.random() * N) + 1;
//            int m = (int) (Math.random() * M) + 1;
//            int k = (int) (Math.random() * n);
//            int[] c = randomArray(m, n);
//            int[] a = randomArray(m, V);
//            int[] b = randomArray(m, V);
//            int ans1 = maxPorfit1(n, m, k, c, a, b);
//            int ans2 = maxPorfit2(n, m, k, c, a, b);
//            if (ans1 != ans2) {
//                System.out.println("出错了!");
//                System.out.println(ans1);
//                System.out.println(ans2);
//                break;
//            }
//        }
//        System.out.println("功能测试结束");
//
//        System.out.println("性能测试开始");
//        int n = 100000;
//        int m = 100000;
//        int v = 1000000;
//        int k = (int) (Math.random() * n);
//        int[] c = randomArray(m, n);
//        int[] a = randomArray(m, v);
//        int[] b = randomArray(m, v);
//        System.out.println("城市数量 : " + n);
//        System.out.println("任务天数 : " + m);
//        System.out.println("收益数值规模 : " + v);
//        long start = System.currentTimeMillis();
//        maxPorfit2(n, m, k, c, a, b);
//        long end = System.currentTimeMillis();
//        System.out.println("运行时间 : " + (end - start) + "毫秒");
//        System.out.println("性能测试结束");

    }
}
