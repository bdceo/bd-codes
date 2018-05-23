package com.bdsoft.y2018.m05;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/5/23 14:15
 */
public class Exam {

    public static void main(String[] args) {


    }

    public static void safeRmListElements() {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(3);
        ints.add(5);
        ints.add(6);
        ints.add(9);

        // 借助迭代器移出
//        Iterator<Integer> ite = ints.iterator();
//        while (ite.hasNext()) {
//            int v = ite.next();
//            if (v % 3 == 0) {
//                ite.remove();
//            }
//        }

        // 循环中不能删除
        for (Integer i : ints) {
            if (i % 3 == 0) {
                ints.remove(i); // ConcurrentModificationException
            }
        }

        System.out.println(ints);
    }

}
