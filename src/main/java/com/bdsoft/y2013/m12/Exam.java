package com.bdsoft.y2013.m12;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import sun.misc.Regexp;

public class Exam {

	public static void main(String[] args) {
		Exam ex = new Exam();
		// 对象引用测试
		ex.ex1223();

		// 观察者模式
		// ex.ex122302();

		// 随机日期，计算当月最后一天是周几
		//		ex.ex122303();
		// 利用日期类库：joda实现
		//		ex.ex12230302();

		// HashMap和Hashtable 的区别
		// ex.ex122304();
		// ArrayList和Vector 的区别
		// ex.ex122305();

		// 正则判断大括号属性
		// ex.ex122701();

		// 正则替换函数使用
		// ex.ex122702();

		// 测试对象，数值引用问题
		// ex.ex122703();
	}

	public void ex1223() {
		Integer sum = null;
		Integer a = 1;
		Integer b = 2;
		// 该方法，测试Integer，实际是对象引用问题，在addSum方法中无法实现计算和功能
		addSum(sum, a, b);
		System.out.println("sum = " + sum);// sum = null

		// 按值传递，可以修改对象内部状态，但是给对象重新复制无效
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

	public void ex122302() {
		BDObject bdo = new BDObject(new BDListenerHandler());
		bdo.event();
	}

	interface BDListener {
		public void doSth();
	}

	class BDListenerHandler implements BDListener {
		public void doSth() {
			System.out.println(this.getClass().getName() + " doSth()");
		}
	}

	class BDObject {
		public List<BDListener> lis = null;

		public BDObject(BDListener bd) {
			lis = new ArrayList<BDListener>();
			lis.add(bd);
		}

		public void event() {
			System.out.println("enter " + this.getClass().getName() + " event()");
			for (BDListener l : lis) {
				l.doSth();
			}
			System.out.println("leave " + this.getClass().getName() + " event()");
		}
	}

	public void ex122303() {
		Date d = new Date();
		int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

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

	public void ex122304() {
		String baseInter = "，实现自接口 java.util.Map";
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String hashMapSuper = hashMap.getClass().getSuperclass().getName();
		System.out.println("HashMap 继承自 " + hashMapSuper + baseInter);
		hashMap.put(null, "1");
		hashMap.put(null, null);
		hashMap.keySet();// Set - Iterator
		hashMap.values();// Collection - Iterator
		System.out.println("\tHashMap默认初始容量：16");
		System.out.println("\tHashMap允许 null 做键和值存储");
		System.out.println("\tHashMap的实现不是同步的，多线程访问，需要自实现外部同步");
		System.out.println("\tHashMap的键值遍历间接依赖 迭代器-Iterator 实现");
		System.out.println("\t除了非同步和允许使用 null 之外，HashMap 类与 Hashtable 大致相同");
		System.out.println();

		Hashtable<String, String> hashTable = new Hashtable<String, String>();
		String hashTableSuper = hashTable.getClass().getSuperclass().getName();
		System.out.println("Hashtable 继承自 " + hashTableSuper + baseInter);
		// hashTable.put(null, "1");
		// hashTable.put("1", null);
		hashTable.put("ont", "1");
		hashTable.elements();// Enumeration
		System.out.println("\tDictionary 类是任何可将键映射到相应值的类的抽象父类，此类已过时");
		System.out.println("\tHashtable默认初始容量：11");
		System.out.println("\tHashtable是同步的");
		System.out.println("\tHashtable中的键和值都不允许是null");
		System.out.println("\tHashtable的键值遍历依赖 枚举-Enumeration 实现");
		System.out.println("\tHashtable特有方法contains，类似方法containsValue功能");
		boolean cnts = hashTable.contains("1");
		System.out.println("\tcontains - cnts=" + cnts);
		cnts = hashTable.containsValue("1");
		System.out.println("\tcontainsValue - cnts=" + cnts);
	}

	public void ex122305() {
		System.out.println("ArrayList：方法均为非线程安全");
		System.out.println("\t当初始容量不足时，扩容为原大小的50%");
		System.out.println("Vector：方法实现了线程安全");
		System.out.println("\t扩容时为原大小的1倍增长");
		System.out.println("LinkedList：适合大容量数据的快速插入");
		System.out.println("\t不适合随机读数据");
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

}