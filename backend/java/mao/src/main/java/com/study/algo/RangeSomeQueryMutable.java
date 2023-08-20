package com.study.algo;
//https://leetcode.ca/2016-10-02-307-Range-Sum-Query-Mutable/

// [2,4,5,7,8,9]
// [0,35,6, 29,12,17,  2,4,5,7,8,9]
// @note: index=0 is no value, since root has no sibling node
public class RangeSomeQueryMutable {
    int[] tree;
    int n;

    public RangeSomeQueryMutable(int[] nums) {
        if (nums != null && nums.length > 0) {
            this.n = nums.length;
            this.tree = new int[nums.length * 2];
            buildTree(nums);
        }
    }

    private void buildTree(int[] nums) {
        for (int i = this.n, j = 0; i < this.tree.length; i++, j++) {
            this.tree[i] = nums[j];
        }
        for (int i = this.n - 1; i > 0; i--) {
            this.tree[i] = this.tree[i * 2] + this.tree[i * 2 + 1];
        }
    }

    public void update(int i, int val) {
        int targetIndex = i + this.n;
        this.tree[targetIndex] = val;
        // bottom up update
        while (targetIndex > 0) {
            int leftIndex = targetIndex;
            int rightIndex = targetIndex;
            // find its node pair
            if (targetIndex % 2 == 0) { // targetIndex on the left
                rightIndex++;
            } else {
                leftIndex--;
            }
            this.tree[targetIndex / 2] = this.tree[leftIndex] + this.tree[rightIndex];
            targetIndex /= 2;
        }
    }

    public int sumRange(int i, int j) {
        int indexi = i + this.n;
        int indexj = j + this.n;
        int sum = 0;
        while (indexi <= indexj) {
            //起点在左 终点在右，可以直接找父节点；起点在右 终点在左 需要把当前节点先算入再向上
            if (indexi % 2 == 1) {
                sum += this.tree[indexi];
                indexi++;
            }
            if (indexj % 2 == 0) {
                sum += this.tree[indexj];
                indexj--;
            }
            indexi /= 2;
            indexj /= 2;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,5,4,6};
        RangeSomeQueryMutable solution = new RangeSomeQueryMutable(nums);
        solution.buildTree(nums);
        System.out.println(solution.sumRange(0,3));
    }
}
