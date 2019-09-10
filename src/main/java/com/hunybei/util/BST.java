package com.hunybei.util;

import org.junit.Test;

/**
 * 二叉树：只有一个根节点，一个节点最多有一个父亲节点，最多有两个孩子节点
 *
 * @param <E>
 */
public class BST<E extends Comparable> {

    private Node root;


    public void add(E e) {
        root = add(root, e);
/*
        if (root == null) {
            root = new Node(e);
        }
        add2(root, e);
*/
    }

    private Node add(Node node, E e) {
        if (node == null) {
            return new Node(e);
        }
        if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        } else if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        }
        return node;

    }

    /**
     * @param node
     * @param e
     */
    public void add2(Node node, E e) {
        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            return;
        }
        if (e.compareTo(node.e) > 0) {
            add2(node.right, e);
        } else if (e.compareTo(node.e) < 0) {
            add2(node.left, e);
        }
    }

    public static class Node<E> {
        Node left;
        Node right;
        E e;

        public Node(E e) {
            this.e = e;
        }

        public Node(Node left, Node right, E e) {
            this.left = left;
            this.right = right;
            this.e = e;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    '}';
        }
    }

    Integer i = new Integer(1);

    /**
     * 二分搜索树的前序遍历
     */
    public void perOrder() {
        perOrder(root);
    }

    private void perOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        perOrder(node.left);
        perOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.e);
        inOrder(root.right);
    }

    public Node minimum() {
        return minimum(root);
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    public Node maximum() {
        return maximum(root);
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public Node removeMax() {
        return removeMax(root);
    }

    private Node removeMax(Node root) {
        if (root.right == null) {
            Node cur = root.left;
            root.right = null;
            return cur;
        }
        root.right = removeMax(root.right);
        return root;
    }

    public Node removeMin() {
        return removeMin(root);
    }

    private Node removeMin(Node root) {
        if (root.left == null) {
            Node cur = root.right;
            root.left = null;
            return cur;
        }
        root.left = removeMin(root.left);
        return root;
    }

    public Node remove(E e) {
        return remove(root, e);
    }

    private Node remove(Node root, E e) {
        if (root == null) {
            return null;
        }
        if (e.compareTo(root.e) > 0) {
            root.right = remove(root.right, e);
            return root;
        } else if (e.compareTo(root.e) < 0) {
            root.left = remove(root.left, e);
            return root;
        } else {
            if (root.left == null) {
                Node cur = root.right;
                root.left = null;
                return cur;
            }
            if (root.right == null) {
                Node cur = root.left;
                root.right = null;
                return cur;
            }
            if (root.right != null && root.left != null) {
                // 找出右子树中最大的元素 ， 目的是为了代替当前要删除的元素
                Node maximum = maximum(root.right);
                maximum.right = removeMax(root.right);
                maximum.left = root.left;
                root.right = root.left = null;
                return maximum;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        BST<Integer> bat = new BST<>();
        int[] arr = {5, 3, 6, 8, 4, 2,34,546,76,34,2,44};
        for (int i : arr) {
            bat.add(i);
        }
        bat.perOrder();
        System.out.println("---===");
        System.out.println(bat.maximum());
        System.out.println(bat.minimum());
        System.out.println("-----------------");
        System.out.println(bat.removeMin());
        bat.perOrder();
        System.out.println("-----------");
        System.out.println(bat.removeMax());
        bat.perOrder();
        bat.remove(3);
        bat.remove(4);
        System.out.println("----------");
        bat.perOrder();
    }


}
