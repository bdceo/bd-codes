package com.bdsoft.bdceo.java8;

import com.bdsoft.bdceo.java8.commonpo.*;
import com.bdsoft.bdceo.java8.commonpo.Currency;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用流收集数据
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/3/29 15:23
 */
public class Chapter06 {

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
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 按交易货币分组
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));

        // 规约和汇总
        long howManyDishs = menu.stream().collect(Collectors.counting());
        howManyDishs = menu.stream().count();

        // 查找流中的最大值和最小值
        Comparator<Dish> dishCaloriesComparator = Comparator.comparing(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));

        // 汇总
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        double avgCalories = menu.stream().collect(Collectors.averagingDouble(Dish::getCalories));
        // 汇总统计：最大，最小，总数，平均数，个数
        IntSummaryStatistics menuStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));

        // 连接字符串
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));

        // 广义的规约汇总
        totalCalories = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        mostCalorieDish = menu.stream().collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        // 收集框架的灵活性：以不同的方法执行相同的操作
        totalCalories = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        totalCalories = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
        // 根据情况选择最佳解决方案
        shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
        shortMenu = menu.stream().map(Dish::getName).collect(Collectors.reducing((s1, s2) -> s1 + s2)).get();
//        shortMenu = menu.stream().collect(Collectors.reducing((d1, d2) -> d1.getName() + d2.getName())).get();// 无法编译
        shortMenu = menu.stream().collect(Collectors.reducing("", Dish::getName, (s1, s2) -> s1 + s2));

        // 分组
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        // 多级分组
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(Collectors.groupingBy(
                dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }
        ));
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                })));

        // 按子组收集数据
        Map<Dish.Type, Long> typesCount = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));

        // 把收集器的结果转换成另一种结果
        Map<Dish.Type, Dish> mostCaloricByType2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))
                                , Optional::get)));

        // 与groupinBy联合使用
        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(
                        dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }, Collectors.toSet()
                )));
        caloricLevelsByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(
                        dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }, Collectors.toCollection(HashSet::new)
                )));

        // 分区
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        List<Dish> vegetarianDishes2 = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());

        // 分区+分组
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
        // 分区后找到最热量最高食物
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)));


    }
}
