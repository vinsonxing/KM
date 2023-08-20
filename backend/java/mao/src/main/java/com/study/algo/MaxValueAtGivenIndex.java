package com.study.algo;
//todo
//某种情形下某个值取值最大最小是多少，先找解空间单调性，看是否满足二分条件
public class MaxValueAtGivenIndex {
    static int getMax(int len, int index, int maxSum) {
        int left = 1, right = maxSum;
        int ans = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            if (getMinSum(len, index, mid) <= maxSum) {
                left = mid + 1;
                ans = Math.max(ans, mid);
            }else {
                right = mid;
            }
        }
        return ans;
    }

    static int getMinSum(int len, int index, int maxVal) {
        int leftLen = index;
        int rightLen = len - index;
        int leftStart = maxVal - 1, rightStart = maxVal;
        int leftSum = 0, rightSum = 0;
        while (leftLen > 0) {
            if (leftStart > 1) {
                leftSum += leftStart;
                leftStart--;
            } else {
                leftSum += 1;
            }
            leftLen--;
        }
        while (rightLen > 0) {
            if (rightStart > 1) {
                rightSum += rightStart;
                rightStart--;
            } else {
                rightSum += 1;
            }
            rightLen--;
        }
        return leftSum + rightSum;
    }

    public static void main(String[] args) {
        System.out.println(getMax(4,2,6));
    }
}
