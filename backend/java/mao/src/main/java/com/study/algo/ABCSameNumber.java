package com.study.algo;
//https://www.bilibili.com/video/BV1SL411B71a?p=60&vd_source=d64b61673a1ff62f40f5f6a1f7f407bf
/*
求需要变几个数组，一般枚举可能性，一个行不行
 */
public class ABCSameNumber {
    public static boolean modify(int[] arr,
                                 int more, int moreT,
                                 int less, int lessT) {
        int[] cnt = new int[3];
        cnt[less] = lessT;
        cnt[more] = moreT;
        // 目标
        int aim = arr.length / 3;
        int L = 0;
        int R = 0;
        while (R < arr.length || cnt[more] <= aim) {
            // cnt[more] 窗口之外，多的数有几个？
            if (cnt[more] > aim) {
                // R++ 窗口右边界，右移
                cnt[arr[R++]]--;
            } else if (cnt[more] < aim) {
                cnt[arr[L++]]++;
            } else { // 在窗口之外，多的数，够了！
                // 少的数，和，另一种数other，能不能平均！都是10个！
                if (cnt[less] + R - L < aim) {
                    cnt[arr[R++]]--;
                } else if (cnt[less] + R - L > aim) {
                    cnt[arr[L++]]++;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {0,0,1,0,2,2,0,2,0,0,2,2};
        System.out.println(modify(arr, 0, 6, 1, 1));
        System.out.println(modify(arr, 2, 5, 1, 1));

//        int a = 11;
//        String str = "";
//        while (a > 1) {
//            str = a % 2 + str;
//            a = (int) (a / 2);
//        }
//        str = a + str;
//        System.out.println(str);
    }
}
