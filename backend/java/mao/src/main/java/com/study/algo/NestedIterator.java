package com.study.algo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedIterator implements Iterator<Integer> {
    final Queue<Integer> queue = new LinkedList<Integer>();
    public NestedIterator(List<NestedInteger> list) { //定义传入参数接口类型最重要
        if(list == null || list.size() == 0) {
            return;
        }
        dfs(list);
    }

    private void dfs(List<NestedInteger> list) {
        for(NestedInteger i: list) {
            if(i.isInteger()) {
                queue.offer(i.getInteger());
            } else {
                dfs(i.getList());
            }
        }
    }
    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Integer next() {
        if (!queue.isEmpty()) {
            return queue.poll();
        }
        return null;
    }
}
interface NestedInteger{
    public boolean isInteger();
    public Integer getInteger();
    public List<NestedInteger> getList();
}
