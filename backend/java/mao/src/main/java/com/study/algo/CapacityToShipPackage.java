package com.study.algo;
//https://leetcode.ca/all/1011.html
public class CapacityToShipPackage {
    public static void main(String[] args) {
        int[] weights = new int[]{1,2,3,4,5,6,7,8,9,10};
        System.out.println(shipWithinDays(weights,5));
    }
    public static int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        for (int weight : weights) {
            left = Math.max(weight, left);
            right += weight;
        }
        int capacity = Integer.MAX_VALUE;
        while (left < right) {
            int mid = (left + right) >> 1;
            if(check(mid, weights, days)) {
                right = mid;
                capacity = Math.min(mid, capacity);
            } else{
                left = mid +1;
            }
        }
//        return left; //最佳值结果区间就是right = left = mid，所以return 哪个值都一样
        return capacity;
    }

    private static boolean check(int capacity, int[] weights, int days) {
        int count = 1;
        int sum = 0;
        for (int weight : weights) {
            sum += weight;
            if (sum > capacity) {
                count++;
                sum = weight;
            }
        }
        return count <= days;

    }

}
