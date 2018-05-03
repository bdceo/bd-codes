package com.bdsoft.bdceo.java8;

import java.util.function.DoubleUnaryOperator;

/**
 * 函数式思考
 * <p>
 * 函数式编程，函数或者方法不应该抛出任何异常
 * 引用透明性：无论在何时，何地调用，使用相同的输入总能持续地得到相同的结果
 * <p>
 * 函数式编程的技巧
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/3/29 15:23
 */
public class Chapter14 {

    public static void main(String[] args) {

        DoubleUnaryOperator cvt1 = curriedConverter(124, 444);
        double val = cvt1.applyAsDouble(10);
        System.out.println(val);

    }

    static double converter(double x, double f, double b) {
        return x * f + b;
    }

    static DoubleUnaryOperator curriedConverter(double f, double b) {
        return (double x) -> x * f + b;
    }

}
