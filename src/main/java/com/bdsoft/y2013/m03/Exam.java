package com.bdsoft.y2013.m03;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exam {

	static {
		System.out.println("static-1");
	}
	static {
		System.out.println("static-2");
	}

	private static Exam thiz = new Exam();

	public Exam() {
	}

	public static void main(String[] args) throws Exception {
		Exam test = new Exam();
		// ex0306();
		// test.ex0322();
		// test.ex0328();
		// test.ex0329();
		// test.ex0330();
		// test.ex0331();
		test.ex0410();
		System.out.println("over");
	}
	
	public void ex0415(){
		
	}

	public void ex0410() {
		// add(int index,E element)
		// 在列表的指定位置插入指定元素（可选操作）。将当前处于该位置的元素（如果有的话）和所有后续元素向右移动（在其索引中加 1）。
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add(0, "K" + i);
		}
		System.out.println(list);
	}

	public void ex0331() {
		// 字符串提取数字并拼接
		String str = "abss999dsfa0012903kkk999jj10";
	}

	public void ex0330() {
		// 找出在数组中出现次数最多的元素
		String[] sa = new String[] { "ceo", "bdcto", "ceo", "bd", "ceo", "bd",
				"bdcfo", "bdcoo", "ceo", "bdceo" };
		Map<String, Integer> cm = new HashMap<String, Integer>();
		int len = sa.length;
		for (int i = 0; i < len; i++) {
			String k = sa[i];
			Integer v = cm.get(k);
			if (v == null) {
				v = new Integer(1);
			} else {
				v += 1;
			}
			cm.put(k, v);
		}
		System.out.println(cm.toString());
	}

	public void ex0329() {
		// 有一数组，找出第几大数字的位置，如：第3大的数字在数组中的索引
		int big = 3;
		Integer[] ita = new Integer[] { 13, 98, 20, 47, 63, 0, 37, 5, 10, 18 };
		System.out.println("原始数组 = ");
		Exam.pa(ita);

		// 排序,从大到小,冒泡
		int len = ita.length;
		Integer[] tmp = Arrays.copyOf(ita, len);
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (tmp[i] < tmp[j]) {
					int e = tmp[i];
					tmp[i] = tmp[j];
					tmp[j] = e;
				}
			}
		}
		System.out.println("排序后 = ");
		Exam.pa(tmp);

		// 获取下标big的数组元素
		int n = tmp[big - 1];
		System.out.println("数组中第" + big + "大的数是：" + n);

		// 从原数组遍历获取指定元素的位置
		int i = 0;
		for (; i < len && ita[i] != n; i++) {
		}
		System.out.println(n + "在原数组的索引是：" + i);
	}

	public static <T> void pa(T[] arr) {
		for (int i = 0, len = arr.length; i < len; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public void ex0328() {
		String s = "2";
		StringBuffer sb = new StringBuffer(s);
		try {
			for (int i = 0; i < 3640; i++) {
				sb.append("-");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sb);
	}

	public void ex0322() {
		// 变量标示符规范：不能数字特特殊字符开头或关键字
		// int 2a,-a,#a.const;
		int _a;
		int $a;

		// 邮箱正则
		String email = "bdceo@qq. com";
		String reg = "[\\w[.-]]+@[\\w[.-]]+.[\\w[.-]]+";
		reg = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern pt = Pattern.compile(reg);
		Matcher ma = pt.matcher(email);
		System.out.println(ma.find());

		// 线程让步
		Exam am1 = new Exam();
		Exam am2 = am1;
		synchronized (am1) {
			System.out.println("before-try");
			try {
				am2.wait();
				System.out.println("after-wait");
				System.out.println("try");
			} catch (InterruptedException e) {
				System.out.println("InterruptedException");
			} catch (Exception e) {
				System.out.println("Exception");
			} finally {
				System.out.println("finally");
			}
		}
	}

	public static void ex0306() {
		boolean boo = new Integer("1") == 1;
		System.out.println(boo);

		double dou = 31.29;
		DecimalFormat df = new DecimalFormat("###0.0");
		System.out.println(df.format(dou));

		// java.sql.Date和sql.Timestamp都是针对数据库对应字段时使用
		// 参考：http://xinxinyin666.i.sohu.com/blog/view/217882404.htm
		java.util.Date ud = new java.util.Date();
		Date sd = new Date(ud.getTime());// sql.date
		System.out.println(sd.toString());
		Timestamp time = new Timestamp(ud.getTime());
		System.out.println(time.toString());

		// 继承关系的实例化过程
		B b = new B();

		// 集合中的对象引用:三个集合引用同一个book，任意一个修改都会反映到其他两个集合本身内的元素
		Book bk = new Book("JavaSE", 99);
		List<Book> bks1 = new ArrayList<Book>();
		List<Book> bks2 = bks1;
		List<Book> bks3 = new ArrayList<Book>();
		bks1.add(bk);
		bks2.get(0).setName("JavaSE-bks2");
		bk.setPrice(23);
		bks3.add(bk);
		System.out.print(bks1.get(0).getName() + "/");
		System.out.print(bks2.get(0).getPrice() + "/");
		System.out.println(bks3.get(0).getPrice() + "/");

		// 所有的参数传递都是 传值，从来没有 传引用 这个事实；
		// 所有的参数传递都会在 程序运行栈上 新分配一个 值 的复制品。
		// 参考：http://www.iteye.com/topic/12961
		Book book = new Book("Java-SE6.0", 44);
		System.out.println("0 = " + book);
		modify1(book);
		System.out.println("1 = " + book);
		modify2(book);
		System.out.println("2 = " + book);
	}

	private static void modify1(Book b) {
		// 参数b，相当于外部调用时的Book实例副本，他们的地址引用是一致的
		// 所以调用实例的方法修改内部属性时，外部调用是可以感知变化的
		b.setName("Java-Concurrency");
	}

	private static void modify2(Book b) {
		// 参数b，是函数外Book实例的一个副本，指向栈中同一个对象
		// 但在函数内部，为b重新分配了一个地址，且是局部的，方法执行完毕就销毁
		// 所以，在重新分配内存地址以后，函数内的修改对外部来说是感知不到的，因为这是两个地址的对象
		b = new Book("Spring", 11);
	}

}

class A {
	static {
		System.out.println("A-static");
	}

	public A() {
		System.out.println("A-init");
	}
}

class B extends A {
	static {
		System.out.println("B-static");
	}

	public B() {
		System.out.println("B-init");
	}
}

class Book {
	String name;
	double price;

	@Override
	public String toString() {
		return "Book [name=" + name + ", price=" + price + "]";
	}

	public Book() {
	}

	public Book(String n, double p) {
		this.name = n;
		this.price = p;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
