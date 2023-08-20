package com.study.algo;

import java.util.ArrayList;
import java.util.List;
//https://leetcode.ca/2017-09-18-658-Find-K-Closest-Elements/
public class FindClosestElements {
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int pos = binarySearch(arr, k);
        int len = arr.length;
        int left = pos - 1, right = pos;
        while (left >= 0 && right <= len - 1 && res.size() < x) {
            if (Math.abs(arr[left] - k) < Math.abs(arr[right] - k)) {
                res.add(arr[left]);
                left--;
            } else if (Math.abs(arr[left] - k) > Math.abs(arr[right] - k)){
                res.add(arr[right]);
                right++;
            } else {
                res.add(arr[right]);
                res.add(arr[left]);
                right++;
                left--;
            }
        }
        if (res.size() == x){
            return res;
        } else {
            if(left == -1) {
                while(right <= len -1 && res.size() < x){
                    res.add(arr[right]);
                    right++;
                }
            }
            if(right == len) {
                while(left >= 0 && res.size() < x){
                    res.add(arr[left]);
                    left--;
                }
            }
        }
        return res;
    }

    public static int binarySearch(int[] arr, int key) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (arr[mid] >= key) {
                high = mid;
            } else if (arr[mid] < key) {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 5, 7, 9, 12};
//        System.out.println(binarySearch(arr, 6));
        System.out.println(findClosestElements(arr, 8,4 ));
    }
}

