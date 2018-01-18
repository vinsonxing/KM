package com.vinson.study.lombok;

import com.google.common.collect.Lists;

import lombok.val;

public class ValTest {

    public String testList() {
        val miao = Lists.newArrayList("a", "b", "c");
        val item = miao.get(0);
        return item;
    }

    public static void main(String[] args) {
        ValTest v = new ValTest();
        System.out.println(v.testList());
    }

}
