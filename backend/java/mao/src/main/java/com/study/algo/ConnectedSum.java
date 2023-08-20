package com.study.algo;

import java.util.*;

public class ConnectedSum {

    private static HashMap<Integer, List<Integer>> buildMap(int[] keys, int[] vals) {
        HashMap<Integer, List<Integer>> res = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            int key = keys[i];
            res.computeIfAbsent(key, k -> res.getOrDefault(k, new ArrayList<>())).add(vals[i]);
        }
        return res;
    }

    public static long getSum(int[] from, int[] to, int cnt) {
        if (from == null && to == null || from.length == 0 && to.length == 0) {
            return cnt;
        }
        HashMap<Integer, List<Integer>> fromMap = buildMap(from, to);

        Set<Integer> vis = new HashSet<>();
        Deque<Integer> fdeque = new ArrayDeque<>();
        Deque<Integer> tdeque = new ArrayDeque<>();

        long ans = 0, groupCnt = 0;

        for (int i = 0; i < from.length; i++) {
            if (!vis.contains(from[i])) {
                fdeque.offer(from[i]);
                vis.add(from[i]);
                groupCnt++;
                while (!fdeque.isEmpty() || !tdeque.isEmpty()) {
                    if (!fdeque.isEmpty()) {
                        int f = fdeque.poll();
                        List<Integer> toList = fromMap.get(f);
                        if (toList != null) {
                            for (Integer t : toList) {
                                if (!vis.contains(t)) {
                                    tdeque.offer(t);
                                    vis.add(t);
                                    groupCnt++;
                                }
                            }
                        }
                    }
                    if (!tdeque.isEmpty()) {
                        int t = tdeque.poll();
                        List<Integer> toList = fromMap.get(t);
                        if (toList != null) {
                            for (Integer t1 : toList) {
                                if (!vis.contains(t1)) {
                                    fdeque.offer(t1);
                                    vis.add(t1);
                                    groupCnt++;
                                }
                            }
                        }
                    }
                }
                cnt -= groupCnt;
                ans += Math.ceil(Math.sqrt(groupCnt));
                groupCnt = 0;
            }
        }
        ans += cnt;
        return ans;
    }

    public static void main(String[] args) {
//        int[] from = {1, 1, 2, 3, 7};
//        int[] to = {2, 3, 4, 5, 8};
        int[] from = {1, 1};
        int[] to = {2, 4};
        int count = 4;
        System.out.println(getSum(from, to, count));

    }
}
