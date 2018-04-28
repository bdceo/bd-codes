package com.bdsoft.bdceo.java8;

import com.bdsoft.bdceo.java8.commonpo.Apple;
import com.bdsoft.bdceo.java8.commonpo.CaloricLevel;
import com.bdsoft.bdceo.java8.commonpo.Dish;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * 重构，测试，调试
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/4/23 17:56
 */
public class Chapter08 {

    public static void main(String[] args) {

        // 从匿名内类到Lambda表达式的转换
        int a = 4;
        Runnable r1 = new Runnable() { // 传统方式
            @Override
            public void run() {
                int a = 8; // 屏蔽包含类的属性
                System.out.println("runnable");
            }
        };
        Runnable r2 = () -> { // 新的方式
//            int a = 4; // 可访问到包含类的属性
            System.out.println("runnable");
        };

        // Lambda是根据上下文确定具体类型的
        doSth(new Task() {
            @Override
            public void execute() {
                System.out.println("task");
            }
        });
//        doSth(()->{System.out.println("task");}); // 类型不确定
        doSth((Task) () -> {
            System.out.println("task");
        }); // 强制显式类型


        // 从Lambda表达式到方法引用的转换
        List<Dish> menu = Lists.newArrayList();
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(Collectors.groupingBy(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }));
        // 在 Dish 类中添加 getCaloricLevel 方法
        dishesByCaloricLevel = menu.stream().collect(Collectors.groupingBy(Dish::getCaloricLevel));

        // 多使用静态辅助方法
        List<Apple> inventory = new ArrayList<>();
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort(Comparator.comparing(Apple::getWeight)); // 分组
        int totalCalories = menu.stream().map(Dish::getCalories).reduce(0, (d1, d2) -> d1 + d2);
        totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories)); // 求和

        // 从命令式的数据处理切换到 Stream
        List<String> dishNames = Lists.newArrayList();
        for (Dish dish : menu) {
            if (dish.getCalories() > 300) {
                dishNames.add(dish.getName());
            }
        }
        dishNames = menu.stream().filter(d -> d.getCalories() > 300).map(Dish::getName).collect(Collectors.toList());

        // 策略模式
        Validator v = new Validator(new isNumberic());
        boolean r = v.validate("aaa");
        v = new Validator(new IsAllLowerCase());
        r = v.validate("dewe");
        // 使用lambda表达式
        v = new Validator((s) -> s.matches("[a-z]+"));
        v = new Validator((s) -> s.matches("\\d+"));
        r = v.validate("ddd");

        // 模板方法
        new OnlineBankingLambda().processCustomer(1, (Apple customer) -> {
            System.out.println(customer.getColor());
        });

        // 观察者模式
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");
        // 使用Lambda表达式
        f.registerObserver((String tweet) -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        });

        // 责任链模式
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        String result = p1.handle("Aren't ladbas really sexy?!!");
        System.out.println(result);
        // 使用Lambda表达式替换: UnaryOperator  andThen
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        result = pipeline.apply("Aren't ladbas really sexy?!!");
        System.out.println(result);

        // 使用日志调试
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        numbers.stream().map(x -> x + 17).filter(x -> x % 2 == 0).limit(3).forEach(System.out::println);
        List<Integer> afterNumbers = numbers.stream()
                .peek(x -> System.out.println("from stream: " + x))
                .map(x -> x + 17)
                .peek(x->System.out.println("after map: "+x))
                .filter(x -> x % 2 == 0)
                .peek(x->System.out.println("after filter: "+x))
                .limit(3)
                .peek(x->System.out.println("after limit: "+x))
                .collect(Collectors.toList());

    }

    interface Task {
        void execute();
    }

    public static void doSth(Runnable r) {
        r.run();
    }

    public static void doSth(Task t) {
        t.execute();
    }

}

// 责任链模式
abstract class ProcessingObject<T> {

    protected ProcessingObject<T> successor;

    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }

    public T handle(T input) {
        T r = handleWork(input);
        if (successor != null) {
            return successor.handle(r);
        }
        return r;
    }

    abstract protected T handleWork(T input);
}

class HeaderTextProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return "From Raoul, Mario and Alan: " + input;
    }
}

class SpellCheckerProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return input.replaceAll("labda", "lambda");
    }
}

// 观察者模式
interface Subject {
    void registerObserver(Observer obs);

    void notifyObservers(String tweet);
}

class Feed implements Subject {

    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer obs) {
        observerList.add(obs);
    }

    @Override
    public void notifyObservers(String tweet) {
        observerList.forEach(o -> o.notify(tweet));
    }
}

interface Observer {
    void notify(String tweet);
}

class NYTimes implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("money")) {
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}

class Guardian implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")) {
            System.out.println("Yet another news in London... " + tweet);
        }
    }
}

class LeMonde implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("wine")) {
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}

// 模板方法
abstract class OnlineBanking {

    public void processCustomer(int id) {
        Apple customer = new Apple(); // 模拟从db加载客户的信息
        makeCustomerHappy(customer);
    }

    abstract void makeCustomerHappy(Apple customer);
}

// 采用Lambda替换
class OnlineBankingLambda {

    public void processCustomer(int id, Consumer<Apple> makeCustomerHappy) {
        Apple customer = new Apple(); // 模拟从db加载客户的信息
        makeCustomerHappy.accept(customer);
    }
}


class Validator {
    private ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String s) {
        return strategy.execute(s);
    }
}

interface ValidationStrategy {
    boolean execute(String s);
}

class IsAllLowerCase implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}

class isNumberic implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}