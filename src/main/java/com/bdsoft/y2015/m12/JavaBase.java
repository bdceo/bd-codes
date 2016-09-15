package com.bdsoft.y2015.m12;

import java.util.Random;

/**
 * 大讲台-学习笔记
 */
public class JavaBase {

	/**
	 * 测试入口
	 */
	public static void main(String[] args) {

		char blank = '　';// 全角空格
		System.out.println("\\u" + Integer.toHexString(blank));// \u3000
		
		char comma = '，';// 全角逗号
		System.out.println("\\u" + Integer.toHexString(comma));// \uff0c

		String str = "a b" + blank + "c" + comma + "d";
		System.out.println(str);// a b　c，d
		
		// 替换全角空格，逗号
		str = str.replaceAll("[\\s|\u3000|\uff0c]+", ",");
		System.out.println(str);// a,b,c,d


		System.out.println(Math.pow(10, 1));

		test();

		int i = 716952034;
		System.out.println(new IntFormat(i).toSimpleChinese());

		// 互换两个值
		int a = 3, b = 4;
		System.out.println(a + "#" + b);
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println(a + "#" + b);

		// 冒泡排序
		bubSort();

	}

	public static void bubSort() {
		int[] nums = new int[10];
		// 随机初始化
		for (int i = 0; i < nums.length; i++) {
			nums[i] = new Random().nextInt(100);
		}
		// 输出
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + "\t");
		}
		System.out.println();
		// 排序
		int fc = 1;
		int ex = 1;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length - i - 1; j++) { // 循环46次
				// for (int j = 0; j < nums.length - 1; j++) { // 循环91次
				fc++;
				if (nums[j] > nums[j + 1]) {
					ex++;
					nums[j] = nums[j] + nums[j + 1];
					nums[j + 1] = nums[j] - nums[j + 1];
					nums[j] = nums[j] - nums[j + 1];
				}
			}
		}
		System.out.println(String.format("循环%d次，交换%d次", fc, ex));
		// 输出
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + "\t");
		}
		System.out.println();
	}

	public static void test() {
		int i = 12345;
		StringBuilder sb = new StringBuilder();
		sb.append(i).append(" = ");
		sb.append(i % 100000 / 10000).append("万");
		sb.append(i % 10000 / 1000).append("千");
		sb.append(i % 1000 / 100).append("百");
		sb.append(i % 100 / 10).append("十");
		sb.append(i % 10 / 1);
		System.out.println(sb.toString());
	}

}

class IntFormat {

	int num;

	public IntFormat(int num) {
		if (num > 1000000000) {
			throw new RuntimeException("太大了");
		}
		this.num = num;
	}

	private int len() {
		return String.valueOf(num).length();
	}

	public String toSimpleChinese() {
		int len = len();
		StringBuilder sb = new StringBuilder();
		sb.append(num).append(" = ");
		for (int i = 1; i <= len; i++) {
			Unit unit = Unit.getUnitBySub((int) Math.pow(10, len - i));
			int w = this.num % unit.getMod() / unit.getSub();
			if (w > 0) {
				sb.append(w).append(unit.getName());
				continue;
			}
			sb.append("零");
		}

		return sb.toString();
	}
}

enum Unit {

	GE("个(元)", 1, 10), SHI("十", 10, 100), BAI("百", 100, 1000), QIAN("千", 1000,
			10000), WAN("万", 10000, 100000), SHIWAN("十", 100000, 1000000), BAIWAN(
			"百", 1000000, 10000000), QIANWAN("千", 10000000, 100000000), YI("亿",
			100000000, 1000000000);

	private String name;
	private int sub;
	private int mod;

	private Unit(String name, int sub, int mod) {
		this.name = name;
		this.sub = sub;
		this.mod = mod;
	}

	public static Unit getUnitBySub(int sub) {
		Unit[] us = Unit.values();
		Unit _u = null;
		for (Unit u : us) {
			if (u.getSub() == sub) {
				_u = u;
				break;
			}
		}
		return _u;
	}

	public String getName() {
		return name;
	}

	public int getSub() {
		return sub;
	}

	public int getMod() {
		return mod;
	}

}
