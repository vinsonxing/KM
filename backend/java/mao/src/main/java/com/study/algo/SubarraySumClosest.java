package com.study.algo;

import java.util.*;

//https://github.com/awangdev/leet-code/blob/master/Java/Subarray%20Sum%20Closest.java
public class SubarraySumClosest {
    static class PreSumNode {
        int val, index;
        public PreSumNode(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
    /*
    PQ 只是排序，增删作用，没有维持size, subarray的sum永远不要忘记前缀和
     */

    private static PriorityQueue<PreSumNode> buildPreSumNodes(int[] nums) {
        PriorityQueue<PreSumNode> queue = new PriorityQueue<>(Comparator.comparing(p -> p.val));
        int preSum = 0;
        queue.offer(new PreSumNode(0,0)); //包含左右边界，很重要
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            queue.offer(new PreSumNode(preSum, i + 1));
        }
        return queue;
    }
    public static List<Tuple> subarraySumClosest(int[] nums) {
        Map<Integer, List<Tuple>> map = new HashMap<>();

        int n = nums.length;
        PriorityQueue<PreSumNode> queue = buildPreSumNodes(nums);

        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            PreSumNode p = queue.poll();
            if (!queue.isEmpty()) {
                int temp = queue.peek().val - p.val;
                if (temp <= min) {
                    min = temp;
                    List<Tuple> list = map.getOrDefault(temp, new ArrayList<Tuple>());
                    list.add(new Tuple(p.index, queue.peek().index));
                    map.put(temp, list);
                }
            }
        }
        return map.get(min);
    }

    public static class Tuple{
        private int first;
        private int second;


        public Tuple(int start, int end) {
            if (start < end){
                this.first = start;
                this.second = end - 1;
            } else{
                this.first = end;
                this.second = start - 1;
            }
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "start=" + first +
                    ", end=" + second +
                    '}';
        }
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, -3, -1};
//        int[] nums = new int[]{-3, 1, 1, -2, 2};
        System.out.println(subarraySumClosest(nums));
    }
}
