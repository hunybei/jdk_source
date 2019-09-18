package com.hunybei.util;

class NumArray {

    private class SegmentTree<E> {


        private Merge<E> merge;
        private E[] data;
        private E[] tree;

        public SegmentTree(E[] arr, Merge<E> merge) {
            data = ((E[]) new Object[arr.length]);
            for (int i = 0; i < arr.length; i++) {
                data[i] = arr[i];
            }
            this.merge = merge;
            this.tree = ((E[]) new Object[4 * arr.length]);
            buildSegmentTree(0, 0, data.length - 1);
        }

        private void buildSegmentTree(int treeIndex, int left, int right) {
            if (left == right) {
                System.out.println(left+"sssss");
                tree[treeIndex] = data[left];
                return;
            }
            // 左孩子的索引
            int leftIndex = leftChild(treeIndex);
            // 右孩子的索引
            int rightIndex = rightChild(treeIndex);
            int mid = left + (right - left) / 2;
            buildSegmentTree(leftIndex, left, mid);
            buildSegmentTree(rightIndex, mid + 1, right);
            tree[treeIndex] = merge.oper(tree[leftIndex], tree[rightIndex]);
        }

        public E query(int queryLeft, int queryRight) {
            return query(0, 0, data.length - 1, queryLeft, queryRight);
        }
//                             {-2, 0, 3, -5, 2, -1}; [0 ,2]  [3,4]
        //                               [0,5]  -3
//                               /             \
        //                      [0,2] 1                  [3,5]  -4
        //                /       \                 /     \
//                    [0,1] -2      [2,2] 3       [3,4] -3 [5,5] -1
        //                  /  \                      /  \
        //            [0,0]  [1,1]                [3,3] [4,4]

        private E query(int treeIndex, int l, int r, int queryLeft, int queryRight) {
            if (l == queryLeft && r == queryRight) {
                return tree[treeIndex];
            }
            int mid = l + (r - l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);

            if (queryLeft >= mid + 1) {
                return query(rightTreeIndex, mid + 1, r, queryLeft, queryRight);
            } else if (queryRight <= mid) {
                return query(leftTreeIndex, l, mid, queryLeft, queryRight);
            }

            E leftResult = query(leftTreeIndex, l, mid, queryLeft, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryRight);
            return merge.oper(leftResult, rightResult);
        }

        /**
         * 更新操作
         *
         * @param index
         * @param e
         */
        public void update(int index, E e) {
            update(0, 0, data.length - 1, index, e);
        }

        private E update(int treeIndex, int l, int r, int index, E e) {
            if (r == index && l == index) {
                tree[treeIndex] = e;
                return e;
            }
            int mid = l + (r - l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            if (index >= mid + 1) {
                E update = update(rightTreeIndex, mid + 1, r, index, e);
                tree[treeIndex] = merge.oper(update, tree[leftTreeIndex]);
                return tree[treeIndex];
            } else {
                E update = update(leftTreeIndex, l, mid, index, e);
                tree[treeIndex] = merge.oper(update, tree[rightTreeIndex]);
                return tree[treeIndex];
            }
        }

        private int leftChild(int index) {
            return 2 * index + 1;
        }

        private int rightChild(int index) {
            return 2 * index + 2;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < tree.length; i++) {
                if (tree[i] != null) {
                    sb.append(tree[i]);
                } else {
                    sb.append("null");
                }
                if (i != tree.length - 1) {
                    sb.append(",");
                } else {
                    sb.append("]");
                }
            }
            return sb.toString();
        }
    }

    private interface Merge<E> {

        E oper(E e, E v);
    }

    private SegmentTree<Integer> segmentTree;
    public NumArray(int[] nums) {
        if(nums.length > 0){
            Integer [] arr = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                arr[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(arr,(a,b)-> a+b);
        }
    }
    
    public void update(int i, int val) {
        if(segmentTree == null){
            throw new RuntimeException();
        }
        segmentTree.update(i,val);
    }
    
    public int sumRange(int i, int j) {
        if(segmentTree == null){
            throw new RuntimeException();
        }
        return segmentTree.query(i,j);
    }

    public static void main(String[] args) {
//        [1,3,5]
        //              [0,2]
        //   [0,1]                 [2,2]
        // [0,0]  [1,1]
        int [] nums = {1,3,5};
        NumArray numArray = new NumArray(nums);
        int i = numArray.sumRange(0, 2);
        System.out.println(i);
        numArray.update(1,2);
        int j = numArray.sumRange(0, 2);
        System.out.println(j);
    }
}

