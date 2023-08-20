package com.study.algo;

import java.util.*;

//next great是特征
public class NextGreaterInLinkedList {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(7);
        ListNode l3 = new ListNode(5);
        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(9);
        ListNode l6 = new ListNode(2);
        ListNode l7 = new ListNode(5);
        ListNode l8 = new ListNode(1);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        System.out.println(Arrays.toString(nextLargerNodes(l1)));
        System.out.println(Arrays.toString(nextLargerNodes11(l1)));
    }
    /*
    map 不能处理key重复数据
     */
//    public static Integer[] nextLargerNodes1(ListNode head) {
//        Map<Integer, Integer> res = new LinkedHashMap<Integer, Integer>();
//
//        Deque<Integer> stack = new ArrayDeque<Integer>();
//        for (ListNode node = head; node != null; node = node.next) {
//            res.put(node.val, 0);
//            while (!stack.isEmpty() && stack.peek() < node.val) {
//                int key = stack.pop();
//                res.put(key, node.val);
//            }
//            stack.push(node.val);
//        }
//        return res.values().toArray(new Integer[0]);
//    }

    public static int[] nextLargerNodes(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        for (; head != null; head = head.next) {
            nums.add(head.val);
        }
        Deque<Integer> stk = new ArrayDeque<>();
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && stk.peek() <= nums.get(i)) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                ans[i] = stk.peek();
            }
            stk.push(nums.get(i));
        }
        return ans;
    }

    public static int[] nextLargerNodes11(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        for (; head != null; head = head.next) {
            nums.add(head.val);
        }
        Deque<Integer> stk = new ArrayDeque<>();
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && nums.get(stk.peek()) <= nums.get(i)) {
                ans[stk.pop()] = nums.get(i);
            }
            stk.push(i);
        }
        return ans;
    }
    static class ListNode{
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
