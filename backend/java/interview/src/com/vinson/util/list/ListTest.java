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
            System.err.println("������Ԫ�صĸ����ͱ���������");
            return;
        }
        int number = Integer.parseInt(args[0]);// ������Ԫ�صĸ���
        int count = Integer.parseInt(args[1]);// ����������Ԫ�صĴ���

        List list = new ArrayList();
        addObject(list, number);//�򼯺������number��Ԫ��
        System.out.println("����ArrayList��");
        travelwithoutIterator(list, count);//���õ���������
        travelwithIterator(list, count);//�õ���������
        list = new LinkedList();
        addObject(list, number);//�򼯺������number��Ԫ��
        System.out.println("����LinkedList��");
        travelwithoutIterator(list, count);//���õ���������
        travelwithIterator(list, count);//�õ���������
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
        System.out.println("��ʹ�õ������ļ��ʱ�䣺" + interval);
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
        System.out.println("ʹ�õ������ļ��ʱ�䣺" + interval);
    }

    public static void travel(List list, int count)
    {
        long startTime;
        long endTime;
        if (list instanceof RandomAccess)
        {
            System.out.println("ʵ����RandomAccess�ӿڣ���ʹ�õ�������");
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
            System.out.println("���ʱ�䣺" + interval);
        } else
        {
            System.out.println("ûʵ��RandomAccess�ӿڣ�ʹ�õ�������");
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
            System.out.println("���ʱ�䣺" + interval);
        }
    }
}