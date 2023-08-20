package com.study.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//子数组问题
//https://www.bilibili.com/video/BV1SL411B71a?p=59&spm_id_from=pageDriver&vd_source=d64b61673a1ff62f40f5f6a1f7f407bf
public class MaxIncreasingSubarrayCanDeleteContinuous {

    public static int maxLen1(int[] arr) {
        int ans = max(arr);
        int n = arr.length;
        for (int L = 0; L < n; L++) {
            for (int R = L; R < n; R++) {
                int[] cur = delete(arr, L, R);
                ans = Math.max(ans, max(cur));
            }
        }
        return ans;
    }

    public static int[] delete(int[] arr, int L, int R) {
        int n = arr.length;
        int[] ans = new int[n - (R - L + 1)];
        int index = 0;
        for (int i = 0; i < L; i++) {
            ans[index++] = arr[i];
        }
        for (int i = R + 1; i < n; i++) {
            ans[index++] = arr[i];
        }
        return ans;
    }

    public static int max(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int ans = 1;
        int cur = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                cur++;
            } else {
                cur = 1;
            }
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    // 正式方法
    // 时间复杂度O(N*logN)
    public static int maxLen2(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int n = arr.length;
//        int[] sorted = new int[n];
//        for (int i = 0; i < n; i++) {
//            sorted[i] = arr[i];
//        }
//        Arrays.sort(sorted);
//        SegmentTree st = new SegmentTree(n);
        int[] data = new int[n];
        SegmentTreeSolution1.SegmentTreeNode root = SegmentTreeSolution1.build(data, 0, n - 1);
//        st.update(rank(sorted, arr[0]), 1);
        int[] dp = new int[n];
        dp[0] = 1;
        int ans = 1;
        // 一个数字也不删！长度！
        int cur = 1;
        for (int i = 1; i < n; i++) {
            //int rank = rank(sorted, arr[i]);
            // (dp[i - 1] + 1)
            int p1 = arr[i - 1] < arr[i] ? (dp[i - 1] + 1) : 1;
//			// rank : 就是当前的数字
//			// 1~rank-1 : 第二个信息的max
//            int p2 = rank > 1 ? (st.max(rank - 1) + 1) : 1;
            int p2 = i > 1 ? SegmentTreeSolution1.max(root, 0, i - 1) + 1 : 1;
            dp[i] = Math.max(p1, p2);
            ans = Math.max(ans, dp[i]);
            if (arr[i] > arr[i - 1]) {
                cur++;
            } else {
                cur = 1;
            }
            // 我的当前值是rank
            // 之前有没有还是rank的记录！
            SegmentTreeSolution1.update(root, i, cur);
        }
        return ans;
    }

    public static int rank(int[] sorted, int num) {
        int l = 0;
        int r = sorted.length - 1;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (sorted[m] >= num) {
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

        public int get(int index) {
            return max(index, index, 1, n, 1);
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

    // 为了验证
    public static int[] randomArray(int len, int v) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * v) - (int) (Math.random() * v);
        }
        return arr;
    }

    // 为了验证
    public static void main(String[] args) {
        int n = 10;
        int v = 8;
        int testTime = 1;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int m = (int) (Math.random() * n);
            int[] arr = {1, 4, 2, 5, 6, 8, 9, 7};
            int ans1 = maxLen1(arr);
            int ans2 = maxLen2(arr);
            System.out.println("ans1=" + ans1 + " ans2=" + ans2);
            if (ans1 != ans2) {
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");


    }
}

class SegmentTreeSolution1 {
    public static class Interval {
        int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class SegmentTreeNode {
        public int start, end;
        public int max;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int max) {
            this.start = start;
            this.end = end;
            this.max = max;
            this.left = null;
            this.right = null;
        }
    }

    public static List<Long> intervalSum(int[] A, List<Interval> queries) {
        List<Long> result = new ArrayList<>();
        if (A == null || A.length == 0 || queries == null || queries.size() == 0) {
            return null;
        }
        SegmentTreeNode root = build(A, 0, A.length - 1);
        queries.forEach(q -> {
            long res = max(root, q.start, q.end);
            result.add(res);
        });
        return result;
    }


    static SegmentTreeNode build(int[] A, int start, int end) {
        if (start == end) {
            return new SegmentTreeNode(start, end, A[start]);
        }
        int mid = (start + end) >> 1;
        SegmentTreeNode left = build(A, start, mid);
        SegmentTreeNode right = build(A, mid + 1, end);
        SegmentTreeNode root = new SegmentTreeNode(start, end, Math.max(left.max, right.max));
        root.left = left;
        root.right = right;
        return root;
    }

    public static int max(SegmentTreeNode root, int start, int end) {
        if (start == root.start && end == root.end) {
            return root.max;
        }
        if (root.right != null && start >= root.right.start) {
            return max(root.right, start, end);
        }
        if (root.left != null && end <= root.left.end) {
            return max(root.left, start, end);
        }
        return Math.max(max(root.left, start, root.left.end), max(root.right, root.right.start, end));
    }

    public static void update(SegmentTreeNode root, int index, int value) {
        if (root.start > index && root.end < index) {
            return;
        }
        if (root.start == index && root.end == index) {
            root.max = value;
            return;
        }
        if (root.left != null) {
            update(root.left, index, value);
        }
        if (root.right != null) {
            update(root.right, index, value);
        }
        if (root.left != null && root.right != null) {
            root.max = Math.max(root.left.max, root.right.max);
        }
    }

    static void print(SegmentTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.max + " ");
        print(root.left);
        print(root.right);
    }

    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 5, 8, 9, 10};
//        Interval i1 = new Interval(0, 1);
//        Interval i2 = new Interval(3, 6);
//        Interval i3 = new Interval(2, 6);
//        List<Interval> list = new ArrayList<>();
//        list.add(i1);
//        list.add(i2);
//        list.add(i3);
//        System.out.println(intervalSum(A, list));

        SegmentTreeNode root = build(A, 0, A.length - 1);
        print(root);
        System.out.println();
        update(root, 3, 6);
        print(root);
    }
}
