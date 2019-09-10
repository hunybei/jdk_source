package com.hunybei.util;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class TestLinkedHashSet {

    @Test
    public void test1() {
        java.util.Set set = new HashSet();
        set.add("1000434");
        set.add("1023436");
        set.add("99999");
        set.add("134343434");
        set.add("343434342");
        set.add("5");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ",");
        }
        System.out.println();
        for (Object o : set) {
            System.out.print(o + ",");
        }
    }

}
