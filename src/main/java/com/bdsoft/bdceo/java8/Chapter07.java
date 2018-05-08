package com.bdsoft.bdceo.java8;

import java.util.stream.Stream;

/**
 * 并行数据处理与性能
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/3/29 15:23
 */
public class Chapter07 {

    public static void main(String[] args) {


    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
    }

    public static long parallelSum(long n) {
        // 将顺序流转换成并行流
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
    }


}
