package com.study.algo;

public class LongestPalindromSubstring {
    public static String longestPalindrome(String s) {
        int n = s.length();
        boolean dp[][] = new boolean[n][n];
        int max = 1, start = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (j - i < 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    System.out.println("dp["+i+"]["+j+"]="+dp[i][j]+";dp["+(i+1)+"]["+(j-1)+"]="+dp[i+1][j-1]);
                    dp[i][j] = dp[i+1][j-1] && s.charAt(i) == s.charAt(j); //i 是内层循环
                }
                if(dp[i][j] && max < j - i + 1) {
                    System.out.println("i="+i+";j="+j);
                    max = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + max);
    }

    public static void main(String[] args) {
        String s = "aabbabbacdca";
        System.out.println(longestPalindrome(s));
    }
}
