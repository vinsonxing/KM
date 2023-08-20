package com.study.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
//bilibili
public class MakeASortedMinimumSwaps {
    public static void main(String[] args) {
        int[] A = {3, 100, 5, 10, 20};
        int[] B = {6, 8, 100};
        Arrays.sort(B);
        System.out.println(process(A, B, 0, 0, false));
        //System.out.println(process1(A, B));
    }
/*
贪心不行，因为仅凭借左右相邻元素无法确定是否要换
4,5,100,102,10,20
到10的时候想换102，但此时的100已经没有机会调整
必须两种选择做递归
 */
//    public static int process1(int[] A, int B[]) {
//        TreeSet<Integer> ts = new TreeSet<>();
//        List<Integer> changes = new ArrayList<Integer>();
//        int ans = -1;
//        for (int b : B) {
//            ts.add(b);
//        }
//        for (int i = 1; i < A.length; i++) {
//            if (A[i] > A[i - 1]) {
//                continue;
//            }
//            if (i >= 2 && ts.floor(A[i]) > A[i - 2] || i == 1 && ts.floor(A[i]) != null) {
//                changes.add(i - 1);
//                ans++;
//            } else if (ts.ceiling(A[i - 1]) < A[i + 1]) {
//                changes.add(i);
//                ans++;
//            } else {
//                return -1;
//            }
//        }
//        return ans;
//    }

    public static int process(int[] A, int[] B, int ai, int bi, boolean swap) {
        if (ai == A.length) {
            return 0;
        }
        int preNum = 0;
        if (ai == 0) {
            preNum = Integer.MIN_VALUE;
        } else {
            if (!swap) {
                preNum = A[ai - 1];
            } else {
                preNum = B[bi - 1];
            }
        }
        int p1 = preNum < A[ai] ? process(A, B, ai + 1, bi, false) : Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        int nearest = bs(B, bi, preNum);
        if (nearest != -1) {
            int temp = process(A, B, ai + 1, nearest + 1, true);
            if (temp != Integer.MAX_VALUE) {
                p2 = temp + 1;
            }
        }
        return Math.min(p1, p2);
    }

    private static int bs(int[] b, int start, int num) {
        int ans = -1;
        int l = start;
        int r = b.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (b[m] > num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }
}
