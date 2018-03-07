package com.bdsoft.bdceo.java8;


import com.bdsoft.bdceo.java8.commonpo.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 第二章 通过行为参数化传递代码
 */
public class Chapter02 {

    public static void main(String[] args) {
        // 行为参数化，就是一个方法接受多个不同的行为作为参数，并在内部使用它们，完成不同行为的能力

        // 行为参数化可让代码更好地适应不断变化的要求，减轻未来的工作量

        // 传递代码，就是将新行为作为参数传递给方法

        // 举例：排序
        List<Apple> inventory = new ArrayList<>();
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());
        inventory.sort(Comparator.comparingInt(Apple::getWeight));

        // 举例：线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hi");
            }
        });
        new Thread(() -> {
            System.out.println("hello");
        });
    }
}
