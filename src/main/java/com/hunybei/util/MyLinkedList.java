package com.hunybei.util;

public class MyLinkedList<E> {

    private int size = 0;

    private Node last;
    private Node first;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size > 0 ? true : false;
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public void addLast(E e) {
        Node nowNode = new Node(e, null);
       /* Node l = this.last;
        last = nowNode;
        if (first == null) {
            first = nowNode;
        }else{
            l.next = nowNode;
        }*/
        if(first == null){
            first = nowNode;
            return ;
        }
        Node temp = first;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = nowNode;

        size++;


    }

    private class Node {
        E item;
        Node next;

        public Node(E item, Node next) {
            this.item = item;
            this.next = next;
        }

        @Override
        public String toString() {
            return item + " -> " + this.next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<String> ar = new MyLinkedList<>();
        ar.add("1");
        ar.add("2");
        ar.add("3");
        System.out.println(ar);
    }

    @Override
    public String toString() {
        return "MyLinkedList{" +
                "last=" + first +
                '}';
    }
}

