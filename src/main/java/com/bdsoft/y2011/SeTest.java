package com.bdsoft.y2011;

public class SeTest {

    /**
     * java基础
     */
    public static void main(String[] args) {
//		 floatEQdouble();
        // opeAdd();
//        opeAdd2();
//        opeDev();
//        opeMod();
//         opeEq();
//		opeMb();
        opeIf();
    }

    public static void floatEQdouble() {
        float f1 = 10.0f, f2 = 10.44f;
        double d1 = 10.0, d2 = 10.44;
        // 整数部分相同，且小数都为0，则float等于double
        System.out.println(f1 == d1);// true
        // 整数部分相同，且小数部分不为0但相等，则float不等于double
        System.out.println(f2 == d2);// false
    }

    public static void opeAdd() {
        byte m = 3, n = 5;
        // 加减法运算，结果至少是int型，结果赋值给byte会出错
        // byte t = m + n;需要如下强转赋值
        byte t2 = (byte) (m + n);

        byte x = -4;
        // byte y=-x;
        byte y = (byte) -x;
    }

    public static void opeAdd2() {
        String a = "3+7=";
        String b = "是结果。";
        int x = 3, y = 7;
        // 如果先遇到字符串再加数字，会直接按照字符串拼接处理
        System.out.println(a + x + y); // 3+7=37
        System.out.println(a + (x + y));// 3+7=10
        // 如果先遇到数字相加再加字符串，则会先进行数字运算再拼接字符串
        System.out.println(x + y + b); // 10是结果。
    }

    public static void opeDev() {
        int a = 10, b = 5, c = 100;
        System.out.println("int a = 10, b = 5, c = 100;");
        System.out.println("a / b = " + (a / b)); // 2
        System.out.println("a / c = " + (a / c)); // 0

        double x = 10.0, y = 5.0;
        System.out.println("double x=10.0,y=5.0;int b=5;");
        System.out.println("x / y = " + (x / y)); // 2.0
        System.out.println("x / b = " + (x / b)); // 2.0

        // 浮点数除以0，可以获得正无穷大和负无穷大或非实数
        double m = -10.0;
        System.out.println("double x=10.0,m = -10.0;");
        System.out.println("x / 0 = " + (x / 0)); // Infinity
        System.out.println("m / 0 = " + (m / 0)); // -Infinity

        System.out.println("0 / a = " + (0 / a)); // 0

        // java.lang.ArithmeticException: / by zero
        // System.out.println("a / 0 = " + (a / 0));
        // System.out.println("0 / 0 = " + (0 / 0));

        System.out.println("0.0 / 0 = " + (0.0 / 0)); // NaN
    }

    // 取模运算，结果符号由左边数决定
    // 有一个浮点型，结果就为浮点型
    // 左边数小于右边，结果为左边数自身
    public static void opeMod() {
        System.out.println("15 % 4 = " + (15 % 4)); // 3
        System.out.println("-15 % 4 = " + (-15 % 4)); // -3

        System.out.println("6.8 % 4.3 = " + (6.8 % 4.3)); // 2.5
        System.out.println("6.8 % 9.3 = " + (6.8 % 9.3)); // 6.8
        System.out.println("0 % 12.9 = " + (0 % 12.9)); // 0.0
        System.out.println("12.9 % 0 = " + (12.9 % 0)); // NaN
    }

    public static void opeEq() {
        System.out.println(12.0 == 12); // true
        System.out.println('a' == 97.0); // true
        System.out.println("A -> Z = " + (int) 'A' + "-" + (int) 'Z');
        System.out.println("a -> z = " + (int) 'a' + "-" + (int) 'z');
    }

    // 移位运算特例
    // 先计算要移动的位数，是否超过数据类型的宽度，超了，不做移位操作，结果等于原值
    public static void opeMb() {
        int i = 88 >> 32;
        long ln = 100 >> 64;
        System.out.println("88 >> 32 = " + i);
        System.out.println("100 >> 64 = " + ln);
        // 解释：在移位运算前，java系统首先把要移的位数与被移数的数据类型位数求余
        // --> i = 88>>32 --> 32(要移的位数)%32(int类型88所占的位数)
        // --> ln= 100>>64 --> 64(要移的位数)%64(long类型100所占的位数)
        // 然后移动余数个位数
    }

    public static void opeIf() {
        boolean b = false;
        if (b = true) {// b=true 表达式返回b的值
            System.out.println("b = true");
        }
    }

}
