package com.bdsoft.bdceo.jvm.cpl;

public class Test {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 128;
        Integer f = 128;
        Long g = 3L;

        int h = 128;
        int i = 128;

        System.out.println(c == d);// true
        // 小于128的整数，==返回true，大于等于128的整数，==放回false
        System.out.println(e == f);// false

        // "=="运算符 遇到算术运算会拆箱，此处a+b会返回int，c也会还原为int
        System.out.println(c == (a + b));// true
        // equals，不会处理数据转型的关系，只会关注数值内容
        System.out.println(c.equals(a + b));// true
        System.out.println(g == (a + b));// true
        // equals，不会处理数据转型的关系，只会关注数值内容，但是此处lang不能和int比较
        System.out.println(g.equals(a + b));// false

        System.out.println(h == i); // true
    }

}
