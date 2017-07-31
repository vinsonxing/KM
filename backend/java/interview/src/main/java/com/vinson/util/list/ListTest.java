package com.vinson.util.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;

public class ListTest
{

    public static void main(String[] args)
    {
    	args = new String[2];
    	args[0] = "100";
    	args[1] = "10000";
        if (args.length == 0)
        {
            System.err.println("请输入元素的个数和遍历次数！");
            return;
        }
        int number = Integer.parseInt(args[0]);// 集合中元素的个数
        int count = Integer.parseInt(args[1]);// 遍历集合中元素的次数

        List list = new ArrayList();
        addObject(list, number);//向集合中添加number个元素
        System.out.println("遍历ArrayList：");
        travelwithoutIterator(list, count);//不用迭代器遍历
        travelwithIterator(list, count);//用迭代器遍历
        list = new LinkedList();
        addObject(list, number);//向集合中添加number个元素
        System.out.println("遍历LinkedList：");
        travelwithoutIterator(list, count);//不用迭代器遍历
        travelwithIterator(list, count);//用迭代器遍历
    }

    /** */
    /**
     * *
     * 
     * @param args
     */
    public static void addObject(List list, int n)
    {
        for (int m = 1; m <= n; m++)
        {
            list.add("" + m);
        }
    }

    public static void travelwithoutIterator(List list, int count)
    {
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        for (int a = 1; a <= count; a++)
        {
            for (int i = 0; i < list.size(); i++)
            {
                list.get(i);
            }
        }
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("不使用迭代器的间隔时间：" + interval);
    }

    public static void travelwithIterator(List list, int count)
    {
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        for (int a = 1; a <= count; a++)
        {
            for (Iterator iter = list.iterator(); iter.hasNext();)
            {
                iter.next();
            }
        }
        endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println("使用迭代器的间隔时间：" + interval);
    }

    public static void travel(List list, int count)
    {
        long startTime;
        long endTime;
        if (list instanceof RandomAccess)
        {
            System.out.println("实现了RandomAccess接口，不使用迭代器！");
            startTime = System.currentTimeMillis();
            for (int a = 1; a <= count; a++)
            {
                for (int i = 0; i < list.size(); i++)
                {
                    list.get(i);
                }
            }
            endTime = System.currentTimeMillis();
            long interval = endTime - startTime;
            System.out.println("间隔时间：" + interval);
        } else
        {
            System.out.println("没实现RandomAccess接口，使用迭代器！");
            startTime = System.currentTimeMillis();
            for (int a = 1; a <= count; a++)
            {
                for (Iterator iter = list.iterator(); iter.hasNext();)
                {
                    iter.next();
                }
            }
            endTime = System.currentTimeMillis();
            long interval = endTime - startTime;
            System.out.println("间隔时间：" + interval);
        }
    }
}