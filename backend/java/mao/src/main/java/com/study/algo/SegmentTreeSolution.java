package com.study.algo;

import java.util.ArrayList;
import java.util.List;

public class SegmentTreeSolution {
    public static class Interval {
        int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class SegmentTreeNode {
        public int start, end;
        public long sum;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, long sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
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
            long res = query(root, q.start, q.end);
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
        SegmentTreeNode root = new SegmentTreeNode(start, end, left.sum + right.sum);
        root.left = left;
        root.right = right;
        return root;
    }

    private static long query(SegmentTreeNode root, int start, int end) {
        if (start == root.start && end == root.end) {
            return root.sum;
        }
        if (root.right != null && start >= root.right.start) {
            return query(root.right, start, end);
        }
        if (root.left != null && end <= root.left.end) {
            return query(root.left, start, end);
        }
        return query(root.left, start, root.left.end) + query(root.right, root.right.start, end);
    }

    public static void update(SegmentTreeNode root, int index, int value) {
        if (root.start > index && root.end < index) {
            return;
        }
        if (root.start == index && root.end == index) {
            root.sum = value;
            return;
        }
        if (root.left != null) {
            update(root.left, index, value);
        }
        if (root.right != null) {
            update(root.right, index, value);
        }
        if (root.left != null && root.right != null) {
            root.sum = root.left.sum + root.right.sum;
        }
    }

    static void print(SegmentTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.sum + " ");
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
