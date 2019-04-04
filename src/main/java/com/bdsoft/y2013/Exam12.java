package com.bdsoft.y2013;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exam12 {

    public static void main(String[] args) {
        Exam12 ex = new Exam12();

//		ex.ex1223();// 对象引用测试
//      ex.ex122703();// 测试对象，数值引用问题

//      ex.ex122303();// 随机日期，计算当月最后一天是周几
//   	ex.ex12230302();// 利用日期类库：joda实现

//      ex.ex122701(); // 正则判断大括号属性
//      ex.ex122702(); // 正则替换函数使用
    }

    public void ex1223() {
        Integer sum = null;
        Integer a = 1;
        Integer b = 2;
        // 该方法，测试Integer，实际是对象引用问题，在addSum方法中无法实现计算和功能
        addSum(sum, a, b);
        System.out.println("sum = " + sum);// sum = null

        // 按值传递，可以修改对象内部状态，但是给对象重新赋值无效
        Date d1 = new Date();
        setDate(d1);
        System.out.println("after setDate=" + d1.toLocaleString());
    }

    public static void addSum(Integer sum, Integer a, Integer b) {
        sum = a + b;
    }

    public static void setDate(Date d) {
        // 加1个月
        d = new Date(d.getYear(), d.getMonth() + 1, d.getDate());
        System.out.println("setDate = " + d.toLocaleString());
    }

    public void ex122303() {
        Date d = new Date();
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        Random rd = new Random();
        long month = 1000 * 60 * 60 * 24 * 29;
        long tmp = month * rd.nextInt(50);
        long time = d.getTime() - tmp;
        d = new Date(time);
        System.out.println("随机时间：" + d.toLocaleString());

        Calendar can = Calendar.getInstance();
        can.setTime(d);
        int year = can.get(Calendar.YEAR);
        int mon = can.get(Calendar.MONTH) + 1;
        int day = can.get(Calendar.DATE);
        int week = can.get(Calendar.DAY_OF_WEEK) - 1;
        week = week == 0 ? 7 : week;
        System.out.println("解析提取：" + year + "-" + mon + "-" + day + " 是周 " + week);

        // 提取本月最后一天
        day = days[mon - 1];
        if ((mon == 2) && (year % 4 == 0)) {
            day = 29;
            System.out.println("闰年->" + year);
        }
        can.set(Calendar.DATE, day);
        week = can.get(Calendar.DAY_OF_WEEK) - 1;
        week = week == 0 ? 7 : week;
        System.out.println("本月最后：" + year + "-" + mon + "-" + day + " 是周 " + week);
    }

    public void ex12230302() {
        DateTime dt = new DateTime(2014, 2, 20, 22, 45, 32, 123);
        System.out.println(dt.toString());

        dt = dt.withDayOfMonth(14);
        System.out.println(dt.toString());

        String dfmt = "yyyy-MM-dd HH:mm:ss SSS";
        DateTimeFormatter fmt = DateTimeFormat.forPattern(dfmt);
        String sdt = fmt.print(dt);
        System.out.println(sdt);
        dt = fmt.parseDateTime("2014-10-04 22:45:32 123");
        System.out.println(dt.toString(dfmt));

        dt = dt.dayOfMonth().withMinimumValue();
        System.out.println(dt.toString(dfmt));// 本月第一天
        dt = dt.dayOfMonth().withMaximumValue();
        System.out.println(dt.toString(dfmt));// 本月最后一天
        int wk = dt.dayOfWeek().get();
        System.out.println(wk);// 本月最后一天是周几
    }

    public void ex122701() {
        String str = "${userName}";
        String reg = "\\$\\{[^\\$\\}\u0020]+\\}";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(str);
        System.out.println(mat.find());
    }

    public void ex122702() {
        // \\s - 代表空白字符：[ \t\n\x0B\f\r]
        // \\S - 代表非空白字符：非空白字符：[^\s]
        String reg = "[\\s]+";
        String str = "hello\r\nworld";
        System.out.println("原始字符串：[" + str + "]");

        String res = str.replaceAll(reg, "#");
        System.out.println(res);// hello#world

        res = str.replace(reg, "#");
        System.out.println(res);// hello\nworld
    }

    public void ex122703() {
        int i = 1;
        add(i);// 2
        System.out.print("," + i + ",");// ,1,

        String si = "1";
        add(si);// 11
        System.out.print("," + si + ",");// ,1,

        StringBuffer sb = new StringBuffer("1");
        add(sb);// 11
        System.out.print("," + sb.toString());// ,11
        // 完整输出 2,1,11,1,11,11
        System.out.println("--------");

        // 数组是引用传递，方法内的修改会影响外部的值
        int[] arr = new int[]{1, 2, 3};
        add(arr);
        Arrays.stream(arr).forEach(System.out::print);
    }

    public void add(int i) {
        i++;
        System.out.print(i);
    }

    public void add(String i) {
        i = i + "1";
        System.out.print(i);
    }

    public void add(StringBuffer sb) {
        sb.append("1");
        System.out.print(sb.toString());
    }

    public void add(int[] arr) {
        arr[0] = 0;
//        arr[0]++;
        System.out.println("修改：" + arr[0]);
    }

}