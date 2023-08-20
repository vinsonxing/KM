package com.study.algo;

import java.util.Arrays;
//最大的商品数量对应至少需要的商店个数，二分的标准-》问题中求最大，二分的标准往往对应最小
public class MinimizedMaximumProductsToStore {
    static boolean check(int n, int maxProd, int[] quantities) {
        int sum = Arrays.stream(quantities).sum();
        if (maxProd * n < sum) {
            return false;
        }
        int storeNum = 0;
        for (int quantity : quantities) {
            storeNum += quantity % maxProd > 0 ? quantity / maxProd + 1 : quantity / maxProd;
        }
        if (storeNum <= n) {
            return true;
        }
        return false;
    }

    static int getProdCount(int[] quantities, int n) {
        int left = 1, right = Arrays.stream(quantities).min().orElse(0);
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(n, mid, quantities)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] quantities = new int[]{15,10,10};
        System.out.println(getProdCount(quantities, 7));
    }
}
