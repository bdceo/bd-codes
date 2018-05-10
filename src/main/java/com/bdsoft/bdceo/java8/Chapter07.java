package com.bdsoft.bdceo.java8;

import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 并行数据处理与性能
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/3/29 15:23
 */
public class Chapter07 {

    public static void main(String[] args) {
        // 初始化递增数字流
        Stream<Long> longStream = Stream.iterate(1L, i -> i + 1).limit(10);
//        longStream.forEach(System.out::println);

        // 测试性能
        System.out.println("Sequential sum done in:" +
                Chapter07.measureSumPerf(Chapter07::sequentialSum, 10_000_000) + " msecs");
        System.out.println("IterativeSum sum done in:" +
                Chapter07.measureSumPerf(Chapter07::iterativeSum, 10_000_000) + " msecs");
        System.out.println("ParallelSum sum done in:" +
                Chapter07.measureSumPerf(Chapter07::parallelSum, 10_000_000) + " msecs");
        // 性能测试结果：传统迭代最快，流式和并行流式处理并不理想
        // 原因：Stream.iterate 涉及前后数值依赖，以及计算累加时的装箱操作

        // 使用更有针对性的方法
        System.out.println("sequential2 sum done in:" +
                Chapter07.measureSumPerf(Chapter07::sequentialSum2, 10_000_000) + " msecs");
        System.out.println("parallelSum2 sum done in:" +
                Chapter07.measureSumPerf(Chapter07::parallelSum2, 10_000_000) + " msecs");

        // 正确使用并行流
        // 错误原因是使用的算法改变了某些共享状态
        System.out.println("sideEffectSum sum done in:" +
                Chapter07.measureSumPerf(Chapter07::sideEffectSum, 10_000_000) + " msecs");
        System.out.println("sideEffectParallelSum sum done in:" +
                Chapter07.measureSumPerf(Chapter07::sideEffectParallelSum, 10_000_000) + " msecs");

        // 高效使用并行流：测量性能，留意装箱-尽量使用IntStream、LongStream、DoubleStream
        // 有些操作并行流比顺序流差-limit、findFirst
        // 较小数据量没必要用并行流
        // 考虑数据结构是否易于分解
        // 考虑流的中间操作和终端操作代价

        // 合并、分支框架：将可以并行的任务拆分成更小的子任务，然后将每个子任务的结果合并生成整体结果
        System.out.println("forkJoinSum sum done in:" +
                Chapter07.measureSumPerf(Chapter07::forkJoinSum, 10_000_000) + " msecs");

        // Spliterator：可分迭代器，遍历数据源，为了并行执行而设计
        String SENTENCE =
                " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");

        // 以函数式风格重写单词计数器
        Stream<Character> charStream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords(charStream) + " words");

        // 并行化，并且会出现字数统计错误，要确保String不是在随机位置拆开，只能在词尾拆开
        charStream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        System.out.println("Found " + countWords(charStream.parallel()) + " words");

        // 实现一个Spliterator
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        charStream = StreamSupport.stream(spliterator, true); // true-创建一个并行流
        System.out.println("Found " + countWords(charStream) + " words");


    }

    // 流式处理
    public static int countWords(Stream<Character> charStream) {
        // 累加，合并
        WordCounter wc = charStream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wc.getCounter();
    }

    // 字数统计
    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }

    // 合并分支
    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinSumCalc fjsc = new ForkJoinSumCalc(numbers);
        return new ForkJoinPool().invoke(fjsc);
    }

    // 流式
    public static long sideEffectSum(long n) {
        Accumulator acc = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(acc::add);
        return acc.total;
    }

    // 并行流：共享可变状态会影响并行流以及并行计算
    public static long sideEffectParallelSum(long n) {
        Accumulator acc = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(acc::add);
        return acc.total;
    }

    // 测量流性能
    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }

    // 流式处理
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1) // 数字累加
                .limit(n) // 限制求和数字个数
                .reduce(0L, Long::sum); // 求和
    }

    // LongStream.rangeClosed 直接生成long，减少装箱操作，容易拆分数字范围，进行并行处理
    public static long sequentialSum2(long n) {
        return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
    }

    // 传统语法
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    // 将顺序流转换成并行流，调用 parallel 之后进行的所有操作都并行执行
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel() // 并行流
                .reduce(0L, Long::sum);
    }

    // LongStream.range 的并行版
    // 使用正确的数据结构然后使其并行工作能够保证最佳的性能
    public static long parallelSum2(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }


}

class Accumulator {

    public long total = 0;

    public void add(long value) {
        total += value; // 非原子操作
    }
}


// 分支合并框架
class ForkJoinSumCalc extends RecursiveTask<Long> {

    private long[] numbers;
    private int start;
    private int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalc(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalc(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length < THRESHOLD) {
            return computeSequentially();
        }

        // 创建一个子任务：数组前半部分
        ForkJoinSumCalc leftTask = new ForkJoinSumCalc(numbers, start, start + length / 2);
        // 异步执行子任务
        leftTask.fork();

        // 创建另一个子任务：数组后半部分
        ForkJoinSumCalc rightTask = new ForkJoinSumCalc(numbers, start + length / 2, end);
        // 同步执行第二个子任务
        Long rightResult = rightTask.compute();

        // 读取第一个子任务结果：join会阻塞等待任务返回结果，所以在两个任务开始之后调用
        Long leftResult = leftTask.join();
        // 结合两个任务的结果
        return rightResult + leftResult;
    }

    /**
     * 顺序执行
     */
    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

}

class WordCounter {

    private int counter;
    private boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    // 类似迭代算法，一个个字符遍历计算
    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    // 合并两个WordCounter
    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(this.counter + wordCounter.getCounter(), wordCounter.isLastSpace());
    }

    public int getCounter() {
        return counter;
    }

    public boolean isLastSpace() {
        return lastSpace;
    }
}

// 迭代器拆分
class WordCounterSpliterator implements Spliterator<Character> {

    private String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    // 处理当前字符
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        // 如果还有字符要处理，继续返回true
        return currentChar < string.length();
    }

    // 拆分逻辑
    @Override
    public Spliterator<Character> trySplit() {
        // 最少10个字符起处理
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;
        }

        // 拆分位置，从中间开始
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            if (Character.isWhitespace(string.charAt(splitPos))) {
                // 创建新的，从开始到拆分的位置
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                // 将当前迭代处理的起始位置
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
