package com.study.algo;

import java.util.Random;

public class RandomInLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private static ListNode head;
    private static Random random = new Random();

    public RandomInLinkedList(ListNode head) {
        this.head = head;
    }

    public static int getRandom() {
        int ans = 0, n = 0;
        for (ListNode node = head; node != null; node = node.next) {
            ++n;
            int rand = random.nextInt(n);
//            int x = 1 + rand;
//            if (n == x) {
//                ans = node.val;
//            }
              if(rand == 0){
                  ans = node.val;
              }
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode n1 =  new ListNode(1);
        ListNode n2 =  new ListNode(2);
        ListNode n3 =  new ListNode(3);
        ListNode n4 =  new ListNode(4);
        ListNode n5 =  new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        RandomInLinkedList list = new RandomInLinkedList(n1);
        for(int i=0; i<100; i++){
            System.out.println(getRandom());
        }
    }
}
