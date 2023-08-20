package com.study.algo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

//https://leetcode.ca/2015-12-23-23-Merge-k-Sorted-Lists/
//The time complexity is O(n * k * logk), and the space complexity is O(k)
public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // put 1st of each list to heap
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
       // (a, b) -> a.val - b.val
        //);

        //
        Arrays.stream(lists).filter(Objects::nonNull).forEach(heap::offer);

        while (heap.size() != 0) {
            ListNode polled = heap.poll();

            current.next = polled;
            current = current.next;

            if (polled.next != null) {
                heap.offer(polled.next); // @note: heap.offer()参数不能是null
            }
        }

        return dummy.next;
    }
}
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
