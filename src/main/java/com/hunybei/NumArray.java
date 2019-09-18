package com.hunybei;

class NumArray {

    private int[] sum;

    private int[] data;

    public NumArray(int[] nums) {
        sum = new int[nums.length + 1];
        data = new int[nums.length];

        sum[0] = 0;
        for (int i = 1; i < nums.length + 1; i++) {
            sum[i] = nums[i - 1] + sum[i - 1];
            data[i - 1] = nums[i - 1];
        }

    }

    public void update(int i, int val) {
        if (i >= data.length || i < 0) {
            throw new RuntimeException();
        }
        data[i] = val;
        for (int j = i + 1; j < sum.length; j++) {
            sum[j] = data[j - 1] + sum[j - 1];
        }
    }


    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    public static void main(String[] args) {
        int [] nums = {1,3,5};
        NumArray numArray = new NumArray(nums);
        int i = numArray.sumRange(0, 2);
        System.out.println(i);
        numArray.update(1,2);
        int j = numArray.sumRange(0, 2);
        System.out.println(j);
    }
}