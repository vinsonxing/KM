package com.study.algo;
//todo
import java.util.*;

//https://leetcode.ca/2017-11-09-710-Random-Pick-with-Blacklist/
public class RandomPickWithBlackList  {
    int size;
    Set<Integer> blacklistSet;
    Map<Integer, Integer> map;
    public static void main(String[] args) {
        RandomPickWithBlackList solution = new RandomPickWithBlackList(7, new int[]{1,4,6});
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
    }

    private int pick() {
        Random rand = new Random();
        int num = rand.nextInt(size);
        num = map.getOrDefault(num, num);
        return num;
    }

    public RandomPickWithBlackList(int N, int[] blacklist){
        size = N - blacklist.length;
        blacklistSet = new HashSet<Integer>();
        for (int blacklistNum : blacklist)
            blacklistSet.add(blacklistNum);
        map = new HashMap<Integer, Integer>();
        int num = size;
        for (int blacklistNum : blacklist) {
            if (blacklistNum < size) {
                while (blacklistSet.contains(num))
                    num++;
                map.put(blacklistNum, num);
                num++;
            }
        }
    }

}
