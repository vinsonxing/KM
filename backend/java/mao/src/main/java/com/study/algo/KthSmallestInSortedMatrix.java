package com.study.algo;
//可以用优先队列，只管往里放，出来自是有序

import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.ca/2016-12-12-378-Kth-Smallest-Element-in-a-Sorted-Matrix/
public class KthSmallestInSortedMatrix {


    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || k <= 0) {
            return 0;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val - o2.val;
            }
        });
        for (int i = 0; i < matrix.length; i++) {
            pq.offer(new Node(i, 0, matrix[i][0]));
        }
        while (!pq.isEmpty()) {
            Node n = pq.poll();
            if (k == 1) {
                return n.val;
            }
            if (n.y + 1 < matrix.length) {
                pq.offer(new Node(n.x, n.y + 1, matrix[n.x][n.y + 1]));
            }
            k--;
        }
        return 0;
    }


    private boolean check_bs(int[][] matrix, int mid, int k, int n) {
        int count = 0;
        int i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                count += (i + 1);
                j++;
            } else {
                i--;
            }
        }
        return count >= k;
    }

    public int kthSmallest_bs(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0], right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = (left + right) / 2;
            if (check_bs(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

class Node {
    int x;
    int y;
    int val;

    public Node(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}
