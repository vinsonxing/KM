package com.study.algo;

import java.util.LinkedList;
import java.util.Queue;

public class MaxConcecutiveIII {
    public static int longestOnes(int[] nums, int k) {
        int left = 0, right = 0, len = nums.length;
        int max = 0, changed = 0;
        while (right < len) {

            if (nums[right] == 0) {
                changed++;
            }

            while (changed > k) {
                if(nums[left] == 0) {
                    changed--;
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
            right++;

        }
        return max;
    }

    public static int longestOnes2(int[] nums, int k) {
        int l = 0, r = 0;
        int max = 0;
        while (r < nums.length) {
            if (nums[r] == 0) {
                --k;
            }
            while (k < 0) {
                if (nums[l] == 0) {
                    k++;
                }
                l++;
            }
            max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
    }
    //https://leetcode.ca/2017-03-31-487-Max-Consecutive-Ones-II/

    /*
    Follow up: What if the input numbers come in one by one as an infinite stream?
    In other words, you can't store all numbers coming from the stream as it's too large to hold in memory.
    Could you solve it efficiently?

    The above method cannot be used in the case of follow up,
    because nums[left] needs to access the previous number.
    We can save all the positions of 0 we encountered in a queue,
    so that we know where to move when we need to move left.
     */

    //数组存储省空间往往是存下标
    public int findMaxConsecutiveOnes(int[] nums) {

        int res = 0, left = 0, k = 1;
        Queue<Integer> q = new LinkedList<>();

        for (int right = 0; right < nums.length; ++right) {
            if (nums[right] == 0) q.offer(right);
            if (q.size() > k) {
                left = q.poll() + 1;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;

    }

    public static void main(String[] args) {
        int[] input = new int[]{0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1};
        System.out.println(longestOnes(input, 3));
        System.out.println(longestOnes2(input, 3));
//        Queue<Integer> q = new LinkedList<>();
//        q.offer(1);
//        q.offer(2);
//        q.offer(3);
//        System.out.println(q.poll());
//        System.out.println(q.poll());
//        System.out.println(q.poll());
    }
}
