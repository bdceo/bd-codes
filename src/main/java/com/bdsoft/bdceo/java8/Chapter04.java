package com.bdsoft.bdceo.java8;

import com.bdsoft.bdceo.java8.commonpo.Dish;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 第四章 引入流
 * 声明性，可复合，可并行
 */
public class Chapter04 {

    /**
     * 什么是流，集合与流，内部迭代与外部迭代，中间操作与终端操作
     * <p>
     * 集合讲的是数据，流讲的是计算
     * <p>
     * 元素序列，源，数据处理操作，流水线，内部迭代
     * <p>
     * 只能遍历一次
     * <p>
     * 流操作：中间操作(可以连接起来的流操作)，终端操作(关闭流的操作)
     * <p>
     * 使用流：一个数据源，一个中间操作链，一个终端操作
     */
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        // java7：迭代筛选
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }
        // 排序
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        // 提取
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }

        // java8：流式处理
//        lowCaloricDishesName = menu.stream()
        lowCaloricDishesName = menu.parallelStream()
                .filter(d -> d.getCalories() > 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
    }

}
