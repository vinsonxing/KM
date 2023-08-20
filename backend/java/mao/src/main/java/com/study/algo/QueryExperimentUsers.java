package com.study.algo;

public class QueryExperimentUsers {
    public static int countOnes(int n) {
        String binaryString = Integer.toBinaryString(n);
        int ans = 0;
        for (int i = 1; i <= binaryString.length(); i++) {
            if("1".equals(binaryString.substring(i-1, i))) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countOnes(7));
    }
    // n 人数 m 实验数 q 查询次数
    public static int[] record(int n, int m, int q, int[][] A, int[][] B) {
        int parts = (n + 31) / 32;
        int[][] bitMap = new int[m][parts];
        for (int i = 0; i < n; i++) {
            for (int exp : A[i]) {
                bitMap[exp][i / 32] |= 1 << (i % 32);
            }
        }
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int all = 0;
            for(int j=0;j<parts;j++){
                int status = 0;
                for(int exp:B[i]){
                    status |= bitMap[exp][j];
                }
                all+= countOnes(status);
            }
            ans[i] = all;
        }
        return ans;
    }
}
