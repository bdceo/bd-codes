package com.bdsoft.y2018.m05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 安全删除集合元素：通过迭代器删除，而不是集合自带方法remove
 */
public class Exam {

    public static void main(String[] args) {
        safeRmListElements();
    }

    public static void safeRmListElements() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(3);
        ints.add(5);
        ints.add(6);
        ints.add(9);

        // 借助迭代器移出
        Iterator<Integer> ite = ints.iterator();
        while (ite.hasNext()) {
            int v = ite.next();
            if (v % 3 == 0) {
                ite.remove();
            }
        }

        // 循环中不能删除
        for (Integer i : ints) {
            if (i % 3 == 0) {
                ints.remove(i); // ConcurrentModificationException
            }
        }
        System.out.println(ints);
    }

}
