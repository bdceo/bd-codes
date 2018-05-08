package com.bdsoft.bdceo.java8;

import com.bdsoft.bdceo.java8.commonpo.Dish;
import com.bdsoft.bdceo.java8.commonpo.Trader;
import com.bdsoft.bdceo.java8.commonpo.Transaction;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 使用流：一个数据源，一个中间操作链，一个终端操作
 * <p>
 * 筛选、切片和匹配
 * 查找、匹配和归约
 * 使用数值范围等数值流
 * 从多个源创建流
 * 无限流
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/3/29 15:23
 */
public class Chapter05 {

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

        // 筛选和切片
        // 用谓词筛选，filter，谓词：一个返回boolean的函数
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        // 筛选各异的元素，distinct
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
        // 截断流，limit
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        // 跳过元素，skip
        dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(3)
                .collect(Collectors.toList());

        // 映射
        // 对流中的每一个元素应用函数，map，接受一个函数，应用到每一个元素
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLenghts = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        // 流的扁平化，split之后返回的是String[]，所以最终返回的是List<String[]>
        List<String[]> spWords = words.stream()
                .map(w -> w.split(""))
                .distinct()
                .collect(Collectors.toList());
        // 尝试使用 map 和 Arrays.stream()
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWorkds = Arrays.stream(arrayOfWords);
        // 最终返回的是一个流的列表
        List<Stream<String>> listStreamOfWords = words.stream()
                .map(w -> w.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        // 使用 flatMap，将map生成的单个流，合并起来扁平化为一个流
        // 把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流
        List<String> uniqueCharacters = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        // 测验：给定数组，返回平方
        numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        // 测验：给定列表[1, 2, 3]和列表[3, 4]，应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs.stream().forEach(arr -> {
            Arrays.stream(arr).forEach(i -> System.out.print(i + " "));
            System.out.println();
        });

        // 查找和匹配： allMatch 、 anyMatch 、 noneMatch 、 findFirst 和 findAny
        // 检查谓词是否至少匹配一个元素，anyMatch
        boolean check = menu.stream().anyMatch(Dish::isVegetarian);
        // 检查谓词是否匹配所有元素，allMatch
        check = menu.stream().allMatch(d -> d.getCalories() < 1000);
        // 确保流中没有任何元素与给定的谓词匹配，noneMatch
        check = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
        // 返回当前流中的任意元素，findAny
        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        dish.ifPresent(d -> System.out.println(d.getName()));
        // 查找第一个元素，findFirst
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();

        // 规约
        // 元素求和
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        sum = numbers.stream().reduce(1, (a, b) -> a * b);
        sum = numbers.stream().reduce(0, Integer::sum);
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        // 菜品总数
        sum = menu.stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);
        long count = menu.stream().count();

        // 付诸实践：交易员，交易
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

        // 2011年所有交易，按交易额排序
        transactions.stream().filter(t -> t.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        // 交易员都在哪些不同城市工作过
        transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(Collectors.toList());
        transactions.stream().map(t -> t.getTrader().getCity()).collect(Collectors.toSet());
        // 查找所有来自剑桥的交易员姓名，按字母顺序排序
        transactions.stream().map(t -> t.getTrader()).filter(t -> t.getCity().equals("Cambridge")).distinct().sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
        // 所有交易员姓名，字母排序，将名字拼接
        transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().reduce("", (a, b) -> a + b);
        transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().collect(Collectors.joining());
        // 有没有交易员在米兰工作
        transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        // 打印生活在剑桥的交易员多有交易额
        transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);
        // 最高交易额
        transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        // 找到交易额最小的交易
        Optional<Transaction> smallestTransaction = transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        smallestTransaction = transactions.stream().min(Comparator.comparing(Transaction::getValue));

        // 数值流，避免隐含的装箱成本
        menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        // 映射到数值流
        int calories = menu.stream().mapToInt(Dish::getCalories).sum();
        // 转换回对象流
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();
        // 默认值 OptionalInt
        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
        int maxCa = maxCalories.orElse(1);
        // 数值范围
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0);
        System.out.println(evenNumbers.count());

        // 构建流
        // 由值创建流
        Stream<String> stringStream = Stream.of("bdceo", "bdcoo", "bdcfo", "bdcto");
        stringStream.map(String::toUpperCase).forEach(System.out::println);
        stringStream = Stream.empty();
        // 由数组创建流
        int[] numArr = {1, 2, 3, 4, 5};
        int numSum = Arrays.stream(numArr).sum();
        // 由文件生成流
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("xx.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 有函数生成流
        // 迭代：初始从0开始，每次加2，生成10个
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        // 练习：斐波那切数列:
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")")); // (0, 1), (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21)
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20)
                .map(t -> t[0]).forEach(System.out::println); // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
        // 生成：
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
        IntStream ones = IntStream.generate(() -> 1);
        IntStream twos = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        });
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }

}
