package com.study.algo;

import java.util.*;

//https://leetcode.ca/2016-12-15-381-Insert-Delete-GetRandom-O(1)-Duplicates-allowed/
//独立做
public class InsertDeleteRandom {
    private List<Integer> list; // hold list
    private Map<Integer, LinkedHashSet<Integer>> map; // val -> list of indexes
    Random random;
    public InsertDeleteRandom() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        boolean ans = !map.containsKey(val);

        int loc = list.size();

        list.add(val);

        LinkedHashSet<Integer> indices = map.getOrDefault(val, new LinkedHashSet<>());
        indices.add(loc);
        map.put(val, indices);

        return ans;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).isEmpty()) {
            return false;
        }

        // Get loc of the val to be removed
        int locToRemove = map.get(val).iterator().next(); // Set iterator

        if (locToRemove < list.size() - 1) { // if not last index, then swap to last index

            // Get the number to be swapped
            int numToSwap = list.get(list.size() - 1);

            // Put the tail number to the location to be removed
            list.set(locToRemove, numToSwap);
            map.get(numToSwap).add(locToRemove);

            // Update the loc
            // @note: when swap two are the same val
            // [2,1,1] => remove(1)
            if (map.get(numToSwap).contains(list.size() - 1)) {
                // undo above "map.get(numToSwap).add(locToRemove);" , if it's the last index
                // remove the same duplicate
                map.get(numToSwap).remove(list.size() - 1);
            }
        }

        // Remove the val in Set
        map.get(val).remove(locToRemove);

        // Remove the val
        list.remove(list.size() - 1);

        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        int loc = random.nextInt(list.size());
        return list.get(loc);
    }
}
