package com.study.algo;

//https://leetcode.ca/2020-06-09-1653-Minimum-Deletions-to-Make-String-Balanced/
public class MinimumDeletionToMakeStringBalanced {
    static int minimumDeletion(String s) {
        char[] arr = s.toCharArray();
        int len = s.length();
        int[] dp = new int[len];
        int bcount = 0;
        if (arr[0] == 'b') {
            bcount++;
            dp[0] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 'a') {
                dp[i] = Math.min(dp[i - 1] + 1, bcount);
            } else {
                bcount++;
                dp[i] = dp[i - 1];
            }
        }
        return dp[len - 1];
    }

    /*
    下面想法有个问题，如果开始是a,就没有必须删这种条件成立，本质是这道题的最终状态并不可以根据当前元素是删除还是保留计算最终结果，
    因为最终结果已经是要求次序，就不能分为必须删还是必须保留
    不能二分，并不是删不删都可以，只能一维dp,蕴含遍历位置删除的最小数目
     */
    static int minimumDeletion2(String s) {
        char[] arr = s.toCharArray();
        int len = s.length();
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            } else if (arr[i] == 'a') {
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = dp[i - 1][0] + 1;
            } else {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        return dp[len - 1][0] + dp[len - 1][1];
    }

    static int minimumDeletion1(String s) {
        int leftB = 0, rightA = 0;

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == 'a') {
                rightA++;
            }
        }
        int res = rightA;
        for (char c : chars) {
            if (c == 'a') {
                rightA--;
            } else {
                leftB++;
            }
            res = Math.min(res, rightA + leftB);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "aaaabbbaaaa"; //aaaabbabbbbbb
        System.out.println(minimumDeletion(s));
        System.out.println(minimumDeletion1(s));
        //System.out.println(minimumDeletion2(s));
    }
}
