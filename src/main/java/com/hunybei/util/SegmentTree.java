package com.hunybei.util;

/**
 * @author hunybei
 * @date 2019-9-10 22:09
 */
public class SegmentTree<E> {


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
            System.out.println(treeIndex+"treee");
            tree[treeIndex] = data[left];
            return;
        }
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);
        int mid = left + (left - right) / 2;
        System.out.println(left + "--------" + mid + "-----"+ right+"tree"+leftIndex+"...."+treeIndex);
        buildSegmentTree(leftIndex, left, mid);
        buildSegmentTree(rightIndex, mid + 1, right);
        tree[treeIndex] = merge.oper(tree[leftIndex], tree[rightIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E getIndex(int index) {
        return data[index];
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

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2 - 1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree);
    }
}
