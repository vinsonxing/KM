package com.study.algo;

import java.util.List;
//让返回值携带信息（至少两个）回传，便于累加
public class HappiestParty {
    class Employee {
        public int happiness;
        List<Employee> next;
    }
    class Info
    {
        public int yes;
        public int no;
        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }
    Info maxHappiness(Employee boss) {
        if(boss == null) {
            return new Info(0, 0);
        }
        int yes = boss.happiness;
        int no = 0;
        for(Employee e :boss.next) {
            Info nextInfo = maxHappiness(e);
            yes += nextInfo.no;
            no += Math.max(nextInfo.yes, nextInfo.no);
        }
        return new Info(yes, no);
    }

    public  int maxHappy(Employee head) {
        if (head == null) {
            return 0;
        }
        Info processInfo = maxHappiness(head);
        return Math.max(processInfo.yes, processInfo.no);
    }
}
