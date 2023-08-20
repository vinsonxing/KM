package com.study.algo;
//https://leetcode.ca/all/1526.html
//线段树的好题
public class MinimumNumberOfIncrementsToFormTarget {
    static int[] target;
    static SegmentTreeNode root;

    static int dfs(int start, int end, int base) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return target[start] - base;
        }
        int[] rangeAndMin = queryRangeMin(root, start, end);
        int min = rangeAndMin[0];
        int pos = rangeAndMin[1];
        int sum = min - base;
        sum += dfs(start, pos - 1, min);
        sum += dfs(pos + 1, end, min);
        return sum;

    }

    public static void main(String[] args) {
        target = new int[]{3, 1, 1, 2};
        root = build(target, 0, target.length - 1);
        System.out.println(dfs(0, target.length - 1, 0));
    }

    private static SegmentTreeNode build(int[] A, int start, int end) {
        if (start == end) {
            return new SegmentTreeNode(start, end, A[start], start);
        }
        int mid = (start + end) >> 1;
        SegmentTreeNode left = build(A, start, mid);
        SegmentTreeNode right = build(A, mid + 1, end);
        int min = Math.min(left.min, right.min);
        int pos = min == left.min ? left.pos : right.pos;
        SegmentTreeNode root = new SegmentTreeNode(start, end, min, pos);
        root.left = left;
        root.right = right;
        return root;
    }
    //返回两个信息的线段树
    private static int[] queryRangeMin(SegmentTreeNode root, int start, int end) {
        int[] result = new int[2];
        if (end < root.start || start > root.end) {
            result[0] = Integer.MAX_VALUE;
            result[1] = 0;
            return result;
        }
        if (start == root.start && end == root.end) {
            result[0] = root.min;
            result[1] = root.pos;
            return result;
        }
        if (root.right != null && start >= root.right.start) {
            return queryRangeMin(root.right, start, end);
        }
        if (root.left != null && end <= root.left.end) {
            return queryRangeMin(root.left, start, end);
        }
        int[] leftRes = queryRangeMin(root.left, start, root.left.end);
        int[] rightRes = queryRangeMin(root.right, root.right.start, end);
        if (leftRes[0] < rightRes[0]) {
            return leftRes;
        } else {
            return rightRes;
        }
    }
}

class SegmentTreeNode {
    public int start, end;
    public int min;
    public int pos;
    public SegmentTreeNode left, right;

    public SegmentTreeNode(int start, int end, int min, int pos) {
        this.start = start;
        this.end = end;
        this.min = min;
        this.pos = pos;
        this.left = null;
        this.right = null;
    }
}





