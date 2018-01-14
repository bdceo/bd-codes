package com.bdsoft.bdceo.java8;

import com.bdsoft.bdceo.java8.commonpo.Apple;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 第一章 为什么要关心Java8
 */
public class Chapter01 {

    public static void main(String[] args) {
        // >>>方法和Lambda作为一等公民
        // 文件过滤
        new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
        // java8风格：FileFilter接口只有一个方法
        new File(".").listFiles(File::isHidden);


        // >>>传递代码
        // java8风格：集合过滤，将方法作为参数传递，相当于接口filter的执行逻辑是调用了apple对应的方法
        List<Apple> inventory = new ArrayList<>();
        filterApples(inventory, Apple::isGreenApple);
        filterApples(inventory, Apple::isHeavyApple);
        // java8风格：Lambda表达式
        filterApples(inventory, (Apple a) -> "red".equals(a.getColor()));
        filterApples(inventory, (Apple a) -> a.getWeight() > 10);
        filterApples(inventory, (Apple a) -> a.getWeight() > 100 && "yellow".equals(a.getColor()));

        // >>> 流
    }

    // 通常的集合过滤封装
    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.filter(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    // 定义过滤筛选方法：函数式接口，当然首先是一个接口，然后就是在这个接口里面只能有一个抽象方法。
    // 称为SAM接口，即Single Abstract Method interfaces。
    // 注解检测是否开启函数式接口
//    @FunctionalInterface
    public interface Predicate<T> {
        boolean filter(T t);
//        boolean compare(T a, T b);
    }

    /**
     * 默认方法
     */
    public interface DefaultInterface {
        int add(int a, int b);

        // 具体实现类，可以不重写
        default boolean check() {
            return Boolean.TRUE;
        }
    }

    class DefaultClass implements DefaultInterface {
        @Override
        public int add(int a, int b) {
            if (check()) {
                return a + b + 2018;
            }
            return a + b;
        }
    }
}
