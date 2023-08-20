package com.study.algo;

public class TargetSum {
    int res = 0;
    public int findTargetSumWays(int[] nums, int S) {
        dfs(nums, S, 0);
        return res;
    }

    void dfs(int[] nums, int S, int cur){
        if(cur == nums.length){
            if(S == 0)
                res+=1;
        }else{
            dfs(nums, S-nums[cur], cur+1);
            dfs(nums, S+nums[cur], cur+1);
        }
    }

}
