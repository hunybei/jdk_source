package com.hunybei.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestLinkedList {


    @Test
    public void test1() {
//        new java.util.LinkedList<>()
        System.out.println(100 >> 4);
        LinkedList<Integer> list = new LinkedList<Integer>();
    /*    list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list.get(3));*/
        List<String> arrayList = new java.util.LinkedList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("3");
        arrayList.add("3");
        boolean contains = arrayList.contains(new Date());
        System.out.println(contains);
        System.out.println(Arrays.toString(arrayList.toArray()));
        arrayList.remove("3");
        System.out.println(arrayList);
        java.util.ArrayList<String> arr = new java.util.ArrayList<>();
        arr.add("1");
        arr.add("2");
        arr.add("3");
        arr.add("3");
        arr.add("3");
        System.out.println(arrayList.retainAll(arr));
        System.out.println(arrayList);
        System.out.println(new Integer(1).equals(new Integer(1)));
    }

    @Test
    public void test2() {

        java.util.LinkedList list = new java.util.LinkedList();
        Object element = null;
        try {
//            element = list.addFirst();
            list.add(null);
            list.add(null);
            list.add(null);
            list.add(null);
            System.out.println(list.size());
            System.out.println(.75f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(element);
        list.removeLast();
    }

}
