//dp
package com.study.algo;
/*
在一个数组上遍历，把每一个元素作为终止元素计算最终结果并比较，没有回退，复杂度O(N)
前缀和&后缀和 （可能利用map存储）
 */
//https://www.bilibili.com/video/BV1SL411B71a?p=81&spm_id_from=pageDriver&vd_source=d64b61673a1ff62f40f5f6a1f7f407bf
public class MagicScrollProblem {
    int maxSum(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int p1 = 0;
        for (int num : arr) {
            p1 += num;
        }
        int n = arr.length;
        int[] left = new int[n];
        //left[i] : 0 ~ i 范围上，必须用一次魔法卷轴，最大累加和是多少
        int sum = 0;
        int maxSum = Math.max(0, sum);
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1] + arr[i], maxSum);
            sum += arr[i];
            maxSum = Math.max(sum, maxSum);
        }
        int p2 = left[n - 1];

        int[] right = new int[n];
        sum = arr[n - 1];
        maxSum = Math.max(0, sum);
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1] + arr[i], maxSum);
            sum += arr[i];
            maxSum = Math.max(sum, maxSum);
        }
        int p3 = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            p3 = Math.max(p3, left[i - 1] + right[i]);
        }
        return Math.max(p1, Math.max(p2, p3));
    }
}
