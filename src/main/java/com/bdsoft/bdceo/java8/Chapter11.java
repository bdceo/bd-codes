package com.bdsoft.bdceo.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CompletableFuture 组合式异步编程
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/3/29 15:23
 */
public class Chapter11 {

    static Random random = new Random(System.currentTimeMillis());

    public static List<Shop> shops = Arrays.asList(new Shop("BestPrice"), new Shop("LetsSaveBig"),
//            new Shop("MyFavoriteShop"), new Shop("BuyItAll"), new Shop("WalmMarket"),
//            new Shop("MyFavoriteShop"), new Shop("BuyItAll"), new Shop("WalmMarket"),
            new Shop("711"), new Shop("Sanfeng"), new Shop("Yonghui"), new Shop("Hualian"));


    public static Executor executor = Executors.newFixedThreadPool(Math.min(Chapter11.shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });

    public static void main(String[] args) {
        System.out.println("cpus=" + Runtime.getRuntime().availableProcessors());
        String product = "myPhone27S";

        // 普通Future方式
//        futurePrice();

        // 阻塞串行
        long start = System.nanoTime();
//        System.out.println(findPrices(product));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("阻塞串行：Done in " + duration + " msecs\n\n");

        // 并行流
        start = System.nanoTime();
        System.out.println(findPrices2(product));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("并行流：Done in " + duration + " msecs\n\n");

        // 借助CompleteFuture发起异步请求
        start = System.nanoTime();
        System.out.println(findPrices3(product));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("CompleteFuture异步-不等返回：Done in " + duration + " msecs\n\n");
        // 借助CompleteFuture发起异步请求
        start = System.nanoTime();
        System.out.println(findPrices4(product));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("CompleteFuture异步-等返回：Done in " + duration + " msecs\n\n");
        // 借助CompleteFuture发起异步请求-自定义线程池容量
        start = System.nanoTime();
        System.out.println(findPrices5(product));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("CompleteFuture异步-自定义线程池：Done in " + duration + " msecs\n\n");

        // 模拟多异步任务，串行阻塞
        start = System.nanoTime();
//        System.out.println(findPrices6(product));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("模拟多异步任务，串行阻塞：Done in " + duration + " msecs\n\n");
        // 模拟多异步任务，借助CompleteFuture发起异步请求-自定义线程池容量
        start = System.nanoTime();
        System.out.println(findPrices7(product));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("模拟多异步任务，CompleteFuture异步-自定义线程池：Done in " + duration + " msecs\n\n");

//        System.out.println("findPrices9:");
//        findPrices9(product);

        System.out.println("\n\nfindPrices10:");
        findPrices10(product);
    }

    /**
     * 阻塞流
     */
    public static List<String> findPrices(String product) {
        return Chapter11.shops.stream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
    }

    /**
     * 并行流
     */
    public static List<String> findPrices2(String product) {
        return Chapter11.shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
    }

    /**
     * 借助CompleteFuture发起异步请求
     */
    public static List<CompletableFuture<String>> findPrices3(String product) {
        return Chapter11.shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))).collect(Collectors.toList());
    }

    /**
     * 借助CompleteFuture发起异步请求，两次map
     */
    public static List<String> findPrices4(String product) {
        List<CompletableFuture<String>> tmpData = findPrices3(product);
        return tmpData.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 借助CompleteFuture发起异步请求，两次map【效率最高】
     */
    public static List<String> findPrices5(String product) {
        List<CompletableFuture<String>> tmpData = Chapter11.shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)), executor))
                .collect(Collectors.toList());
        return tmpData.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 模拟多任务异步，阻塞串行流
     */
    public static List<String> findPrices6(String product) {
        return Chapter11.shops.stream().map(shop -> shop.getPrices(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    /**
     * 模拟多任务异步,3次map，借助CompleteFuture发起异步请求
     * thenApply，非阻塞
     * thenCompose，将两个CompletableFuture连接起来
     */
    public static List<String> findPrices7(String product) {
        List<CompletableFuture<String>> tmpData = Chapter11.shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrices(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());

        return tmpData.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 将两个 CompletableFuture 对象整合起来，无论是否有依赖顺序
     * thenCombine，合并两个CompletableFuture
     */
    public static Future<Double> findPrices8(String product) {
        Shop shop = new Shop("bdceo");
        // 合并两个CompletableFuture
        Future<Double> futurePriceInUSD = CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                .thenCombine(CompletableFuture.supplyAsync(() -> ExchargeService.getRate(Money.EUR, Money.USD))
                        , (price, rate) -> price * rate);
        return futurePriceInUSD;
    }

    /**
     * 持续优化，四次map，thenAccept，allOf
     */
    public static void findPrices9(String product) {
        Stream<CompletableFuture<String>> tmpStream =
                Chapter11.shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrices(product), executor))
                        .map(future -> future.thenApply(Quote::parse))
                        .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));

        // 直接输出结果
//        tmpStream.map(f -> f.thenAccept(System.out::println));

        // 所有执行完毕：allOf
        CompletableFuture[] futures = tmpStream.map(f -> f.thenAccept(System.out::println))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures);
    }

    /**
     * 最终版
     */
    public static void findPrices10(String product) {
        Stream<CompletableFuture<String>> tmpStream =
                Chapter11.shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrices(product), executor))
                        .map(future -> future.thenApply(Quote::parse))
                        .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));

        // 随机延迟，每个完成打印耗时信息
        long start = System.nanoTime();
        CompletableFuture[] futures = tmpStream
                .map(f -> f.thenAccept(s -> System.out.println(s + " (done in " + ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

    public static void futurePrice() {
        Shop shop = new Shop("bdceo");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        doSomethingElse();
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs\n\n");
    }

    public static void doSomethingElse() {
        System.out.println("doSomethingElse......");
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }

    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}

// 货币
enum Money {
    EUR, USD;

    Money() {
    }
}

// 汇率计算
class ExchargeService {
    public static double getRate(Money from, Money to) {
        return Chapter11.random.nextDouble();
    }
}

// 优惠
class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(),
                        quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        Chapter11.delay(); // 模拟延迟
        return price * (100 - code.percentage) / 100;
    }
}

class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    /**
     * 使用工厂方法 supplyAsync 创建 CompletableFuture
     */
    public Future<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calcPrice(product));
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calcPrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                e.printStackTrace();
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    public double getPrice(String product) {
        return calcPrice(product);
    }

    public String getPrices(String product) {
        double price = calcPrice(product);
        Discount.Code code = Discount.Code.values()[Chapter11.random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    private double calcPrice(String product) {
        Chapter11.delay();
        return Chapter11.random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Quote {

    private final String shopName;
    private final double price;
    private final Discount.Code discountCode;

    public Quote(String shopName, double price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = code;
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }
}