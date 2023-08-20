package com.study.algo;

public class KthLargestElementInArray {
    public static int findKthLargest(int[] nums, int k) {
       int low = 0, high = nums.length - 1;
       while(low <= high) {
           int pos = partition(nums, low, high); //pos是针对全局的下标，每次排序是局部排序，因为上一轮已经排好
           if(pos == k-1){
               return nums[pos];
           } else if(pos > k-1) {
               high = pos - 1;
           } else {
               low = pos + 1;
           }
       }
       return -1;
    }

    public static int findKthSmallest(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        while(low <= high) {
            int pos = partition1(nums, low, high);
            if(pos == k-1){
                return nums[pos];
            } else if(pos > k-1) {
                high = pos - 1;
            } else {
                low = pos + 1;
            }
        }
        return -1;
    }

    static int partition(int[] nums, int low, int high) { //左边大右边小
        int pivot = nums[low], i = low + 1, j = high;
        while(i <= j){
            if(nums[i] < pivot && nums[j] > pivot) {
                swap(nums, i++, j--);
            }
            if(nums[i] >= pivot) {
                i++;
            }//现在nums[i]是小于pivot的，如果要想左边是大数，就不应该和i 互换
            if(nums[j] <= pivot) {
                j--;
            }//现在nums[j]是大于pivot的，如果要想左边是大数，就应该和j 互换，如果左边是小数，就应该和i互换
        }
        System.out.println("i="+i+";j="+j);
        swap(nums, low, j);
        return j;
    }

    static int partition1(int[] nums, int low, int high) {  //左边小右边大
        int pivot = nums[low], i = low + 1, j = high;
        while(i <= j){ //当i=j时候，左右已经纯了，左边比pivot小，右边比pivot大，但当前数字还没有归类，一定要跨过去归类
            if(nums[i] > pivot && nums[j] < pivot) {
                swap(nums, i++, j--);
            }
            if(nums[i] <= pivot) {
                i++;
            }
            if(nums[j] >= pivot) {
                j--;
            }
        }
        System.out.println("i="+i+";j="+j);
        swap(nums, low, j);
        return j;
    }

    static void swap(int[] nums, int i, int j){
        int temp;
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 0, 6, 4};
        //System.out.println(findKthSmallest(nums, 4));
        System.out.println(findKthLargest(nums, 4));
    }
}
