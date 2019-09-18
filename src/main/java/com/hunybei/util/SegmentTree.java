package com.hunybei.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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

    /**
     * 1   2 - 4
     *
     * @param args
     */
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree);
//        Integer query1 = segmentTree.query(0, 4);
//        Integer query2 = segmentTree.query(0, 3);
        segmentTree.update(0,-1);
        Integer query = segmentTree.query(0, 5);
        System.out.println(segmentTree);
        System.out.println(query);
//        System.out.println(query1);
//        System.out.println(query2);
        try {
//            URL url = new URL("/hello");
            URI uri = new URI("https://www.cnblogs.com/xenny/p/9801703.html");
            URI uri2 = new URI("/p/9801703.html");
            boolean absolute = uri.isAbsolute();
            System.out.println(uri2.isAbsolute());
            System.out.println(absolute);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
