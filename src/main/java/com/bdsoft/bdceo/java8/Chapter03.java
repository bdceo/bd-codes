package com.bdsoft.bdceo.java8;

import com.bdsoft.bdceo.java8.commonpo.Apple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * 第三章 Lambda表达式
 */
public class Chapter03 {

    /**
     * Lambda=匿名函数：没有名称，但是有参数列表，函数主体，返回类型，异常列表，不属于某个特定的类
     * 可以作为参数，传递或赋值
     * Lambda没有return语句，因为已经隐含了return
     * Lambda可以包含多行语句
     */
    public static void main(String[] args) throws IOException {
        List<Apple> inventory = new ArrayList<>();
        // 先前
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        // Lambda之后
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());

        // 在哪里及如何使用Lambda
        // 函数式接口：仅仅定义了一个抽象方法，举例：Comparator，Runnable，Callable，ActionListener，PrivilegedAction
        // 哪怕有很多默认方法，只要接口只定义了一个抽象方法，仍是一个函数式接口
        // 将Lambda结果赋值
        Runnable r1 = () -> System.out.println("hello-Lambda");
        // 匿名类方式
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello-匿名类");
            }
        };
        process(r1);
        process(r2);
        // 直接将Lambda作为函数式接口的实现，当做参数传递
        process(() -> System.out.println("hello-inner-Lambda"));

        // 函数式接口中抽象方法的签名基本上就是Lambda表达式的签名，简称函数描述符
        // 举例：()->void 代表了参数列表空，返回void的函数

        // 付诸实践：环绕执行模式
        // 3.3.1，行为参数化，3.3.4，传递Lambda
        String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        result = processFile((BufferedReader br) -> br.readLine());

        // 使用函数式接口：java.util.function包：Predicate，Consumer，Function……
        // Predicate：接收一个泛型对象，返回一个boolean值：boolean test(T var1);
        // 需要标识一个涉及类型T的布尔表达式时
        Predicate<String> testString = (String s) -> !s.isEmpty();
        List<String> notEmpty = filter(Arrays.asList("dd", ""), testString);

        // Consumer：接收一个泛型对象，不返回值：void accept(T t);
        // 需要访问类型T的对象，并对其执行某些操作
        forEach(Arrays.asList(1, 2, 3), (Integer i) -> System.out.println(i));

        // Function：接收一个泛型T，返回一个泛型R值：R apply(T var1);
        // 将输入对象的信息映射到输出
        List<Integer> sti = map(Arrays.asList("ddd", "xx"), (String s) -> s.length());

        // 类型检查、类型推断以及限制
        // 类型检查：从使用Lambda的上下文推断出来，上下文中需要的类型成为目标类型
        // 类型推断：Java编译器会从上下文目标类型推断出用什么函数式接口来配合Lambda表达式
        // s没有显式类型
        filter(Arrays.asList("dd", ""), s -> !s.isEmpty());

        // 使用局部变量：自由变量——不是参数，而是在外层作用域中定义的变量
        // 局部变量必须显式声明为final或事实上的final，因为Lambda表达式只能补货指派给他们的局部变量一次
        // 对局部变量的限制：不鼓励使用改变外部变量的典型命令式编程模式，Lambda是对值封闭，而不是对变量封闭
        // 局部变量保存在栈上，实例变量保存在堆上，堆是在线程之间共享的

        // 方法引用：如果一个Lambda代表的只是“直接调用这个方法”，那最好还是用方法名称来调用它，而不是去描述如何调用它
        // 根据已有的方法实现来创建Lambda表达式：目标引用::方法名称

        // 如何构建方法引用：指向静态方法，指向任意类型实例方法，指向现有对象的实例方法

        // 构造函数引用：ClassName::new
        Supplier<Apple> ap = Apple::new;
        ap = () -> new Apple();
        Apple apple = ap.get();// 无参构造函数Apple()
        // 多参数
        map(Arrays.asList(12, 31, 44), Apple::new);// 一个参数构造函数Apple(int weight)
        BiFunction<Integer, String, Apple> ap2 = Apple::new;
        ap2 = (weight, color) -> new Apple(12, "red");
        apple = ap2.apply(12, "red");// 两个参数构造函数(int weight, String color)

        // Lambda和方法引用实战：用不同的策略给一个Apple列表排序
        // 传递代码：行为参数化
        inventory.sort(new AppleComparator());
        // 使用匿名类
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        // 使用Lambda表达式
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());
        inventory.sort((a1, a2) -> a1.getWeight() - a2.getWeight());
        // 借助静态方法：方法引用
        Comparator<Apple> c = Comparator.comparingInt((Apple a) -> a.getWeight());
        inventory.sort(Comparator.comparing((a) -> a.getWeight()));
        inventory.sort(Comparator.comparing(Apple::getWeight));

        // 复合Lambda表达式的有用方法

        // 比较器复合：逆序-reversed，比较器链-thenComparing
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));

        // 谓词复合:negate,and,or
        testString.negate();
        testString.and(s -> s.length() > 10).or(s -> s.startsWith("bd"));

        // 函数复合:andThen-先执行f再执行g，compose-先执行g再执行f
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int res = h.apply(1);// 4：g(f(x))
        Function<Integer, Integer> h2 = f.compose(g);
        res = h2.apply(1);// 3：f(g(x))
    }

    public static class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

    // 接受三个参数，返回一个对象
    @FunctionalInterface
    public interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T i : list) {
            result.add(f.apply(i));
        }
        return result;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T i : list) {
            c.accept(i);
        }
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T i : list) {
            if (p.test(i)) {
                results.add(i);
            }
        }
        return results;
    }

    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("d:/快递.txt"))) {
            return br.readLine();
        }
    }

    // 传递行为
    public static String processFile(BufferedReaderProcessor brp) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("d:/快递.txt"))) {
            // 3.3.3，执行一个行为
            return brp.process(br);
        }
    }

    // 3.3.2，使用函数式接口传递行为：匹配(BufferedReader)->String，还抛出IOException异常
    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }

    public static void process(Runnable r) {
        r.run();
    }
}
