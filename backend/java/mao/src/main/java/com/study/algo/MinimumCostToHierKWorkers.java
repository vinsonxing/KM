package com.study.algo;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumCostToHierKWorkers {
    public static class Employee {
        public double rubbishDegree;
        public int quality;

        public Employee(int wage, int quality) {
            this.rubbishDegree = wage / quality;
            this.quality = quality;
        }

        public double minCostToHierWorker(int[] quality, int[] wage, int k) {
            int n = quality.length;
            Employee[] employees = new Employee[n];
            for (int i = 0; i < n; i++) {
                employees[i] = new Employee(wage[i], quality[i]);
            }
            Arrays.sort(employees, (a, b) -> a.rubbishDegree <= b.rubbishDegree ? -1 : 1);
            PriorityQueue<Integer> minTops = new PriorityQueue<Integer>((a, b) -> b - a);
            double ans = Double.MAX_VALUE;
            for (int i = 0, qualitySum = 0; i < n; i++) {
                int currQuality = employees[i].quality;
                qualitySum += currQuality;
                minTops.offer(currQuality);
                if (minTops.size() == k) {
                    ans = Math.min(ans, qualitySum * employees[i].rubbishDegree); //关键点是进来新的employee会被当做一个元素
                    qualitySum -= minTops.poll(); //sum拿出去一个大的，不会影响下次新来的employee
                }

//                if (minTops.size() < k) {
//                    qualitySum += currQuality;
//                    minTops.offer(currQuality);
//                    if (minTops.size() == k) {
//                        ans = Math.min(ans, qualitySum * employees[i].rubbishDegree);
//                    }
//                } else {
//                    //if (minTops.peek() > currQuality) {
//                    qualitySum += currQuality - minTops.poll();
//                    minTops.offer(currQuality);
//                    //}
//                    ans = Math.min(ans, qualitySum * employees[i].rubbishDegree);
//                }
            }
            return ans;
        }

        public static void main(String[] args) {

        }
    }
}
