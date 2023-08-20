package com.study.algo;
//找踩谁最优

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//#47
//https://www.bilibili.com/video/BV1SL411B71a?p=47&vd_source=d64b61673a1ff62f40f5f6a1f7f407bf
/*
线段树的键有序保证最后递增子序列，通过线段树去寻找能够踩谁
和城市完成任务算法不同，它构建的线段树对顺序没有要求，全局去搜索除当前任务城市外的最大收益
而本题要把线段树的有序逻辑利用起来，范围内查找最大 （max 参数是右边界）
但相同点是遍历一遍会根据之前线段树填值判断当前最优结果
*/
public class SortedSubSequenceMaxSum {
    // 为了测试
    // 绝对正确的暴力方法
    public static int right(int[] h, int[] v) {
        return process(h, v, 0, 0);
    }

    public static int process(int[] h, int[] v, int index, int preValue) {
        if (index == h.length) {
            return 0;
        }
        int p1 = process(h, v, index + 1, preValue);
        int p2 = h[index] >= preValue ? (v[index] + process(h, v, index + 1, h[index])) : 0;
        return Math.max(p1, p2);
    }

    // 正式方法
    // 时间复杂度O(N * logN)
    public static int maxSum(int[] h, int[] v) {
        int n = h.length;
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = h[i];
        }
        Arrays.sort(rank);
        SegmentTree st = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            int height = rank(rank, h[i]);
            // 1~height max
            st.update(height, st.max(height) + v[i]);
        }
        return st.max(n);
    }
    //最长递增子序列基础上求对应数值最大累加和
    public static int maxSum2(int[] h, int[] v) {
        int n = h.length;
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = h[i];
        }
        Arrays.sort(rank);
        int[] data = new int[n];
        SegmentTreeSolution1.SegmentTreeNode root = SegmentTreeSolution1.build(data, 0, n - 1);

        for (int i = 0; i < n; i++) {
            int height = rank(rank, h[i]);
            int max = SegmentTreeSolution1.max(root, 0, height - 1);
            SegmentTreeSolution1.update(root, height, max + v[i]);
        }
        return SegmentTreeSolution1.max(root, 0, n - 1);
    }

    // [150, 152, 160, 175]  160
    //   1    2    3    4
    // 3
    public static int rank(int[] rank, int num) {
        int l = 0;
        int r = rank.length - 1;
        int m = 0;
        int ans = 0;
        while (l <= r) {
            m = (l + r) / 2;
            if (rank[m] >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans + 1;
    }

    public static class SegmentTree {
        private int n;
        private int[] max;
        private int[] update;

        public SegmentTree(int maxSize) {
            n = maxSize + 1;
            max = new int[n << 2];
            update = new int[n << 2];
            Arrays.fill(update, -1);
        }

        public void update(int index, int c) {
            update(index, index, c, 1, n, 1);
        }

        public int max(int right) {
            return max(1, right, 1, n, 1);
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt] != -1) {
                update[rt << 1] = update[rt];
                max[rt << 1] = update[rt];
                update[rt << 1 | 1] = update[rt];
                max[rt << 1 | 1] = update[rt];
                update[rt] = -1;
            }
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                max[rt] = C;
                update[rt] = C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
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
            pushDown(rt, mid - l + 1, r - mid);
            int ans = 0;
            if (L <= mid) {
                ans = Math.max(ans, max(L, R, l, mid, rt << 1));
            }
            if (R > mid) {
                ans = Math.max(ans, max(L, R, mid + 1, r, rt << 1 | 1));
            }
            return ans;
        }

    }

    // 为了测试
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v) + 1;
        }
        return ans;
    }

    // 为了测试
    public static void main(String[] args) {
//        int N = 30;
//        int V = 100;
//        int testTime = 20000;
//        System.out.println("测试开始");
//        for (int i = 0; i < testTime; i++) {
//            int n = (int) (Math.random() * N) + 1;
//            int[] h = randomArray(n, V);
//            int[] v = randomArray(n, V);
//            if (right(h, v) != maxSum(h, v)) {
//                System.out.println("出错了!");
//            }
//        }
//        System.out.println("测试结束");
        int[] h = {2, 4, 6, 1, 5, 3};
        int[] v = {100, 20, 40, 10, 50, 30};
        System.out.println(maxSum(h, v));
        System.out.println(maxSum2(h, v));
    }
}


