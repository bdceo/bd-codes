/**
 * JustTest.java
 * com.bdsoft.y2015.m07
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
 */
package com.bdsoft.y2015;

import org.springframework.util.StopWatch;

/**
 * 菲波那切数列，递归测试
 */
public class JustTest {

    static int count = 0;

    /**
     * fab(10) = 55	count=177
     * fab(20) = 6765	count=21891
     * fab(30) = 832040	count=2692537
     * fab(41) = 165580141	count=535828591
     * fab(51) = 20365011074	count=1478050757
     * StopWatch '菲波那切数列计算耗时': running time (millis) = 147252
     * -----------------------------------------
     * ms     %     Task name
     * -----------------------------------------
     * 00001  000%  fab-10
     * 00006  000%  fab-20
     * 00011  000%  fab-30
     * 02011  001%  fab-41
     * 145223  099%  fab-51
     */
    public static void main(String[] args) {
        StopWatch timer = new StopWatch("菲波那切数列计算耗时");
        timer.start("fab-10");
        System.out.println("fab(10) = " + fab(10) + "\tcount=" + count);
        timer.stop();

//        count = 0;
//        timer.start("fab-20");
//        System.out.println("fab(20) = " + fab(20) + "\tcount=" + count);
//        timer.stop();
//
//        count = 0;
//        timer.start("fab-30");
//        System.out.println("fab(30) = " + fab(30) + "\tcount=" + count);
//        timer.stop();
//
//        count = 0;
//        timer.start("fab-41");
//        System.out.println("fab(41) = " + fab(41) + "\tcount=" + count);
//        timer.stop();
//
//        count = 0;
//        timer.start("fab-51");
//        System.out.println("fab(51) = " + fab(51) + "\tcount=" + count);
//        timer.stop();

        timer.start("fab2-51");
        System.out.println("fab2(51)=" + fab2(51));

        timer.stop();
        System.out.println(timer.prettyPrint());
    }

    /**
     * 优化：正推
     */
    static long fab2(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("大于0");
        }
        long[] fab = new long[n+1];
        fab[0] = 0;
        fab[1] = 1;
        for (int i = 2; i <= n; i++) {
            fab[i] = fab[i - 1] + fab[i - 2];
        }
        return fab[n];
    }

    /**
     * 递归计算：逆推
     */
    static long fab(long i) {
        if (i < 0) {
            throw new IllegalArgumentException("大于0");
        }
        count++;
        if (i <= 1) {
            return i;
        }
        return fab(i - 1) + fab(i - 2);
    }

}
