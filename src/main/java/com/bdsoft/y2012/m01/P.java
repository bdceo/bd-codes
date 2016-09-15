package com.bdsoft.y2012.m01;

import java.math.BigDecimal;

/**
 * 排列
 * 
 * @author bdceo
 * 
 */
public class P {

	private int n;// 总元素数
	private int m;// 取出元素个数(m<=n)，一个排列

	public P() {
	}

	public P(int n, int m) {
		this.n = n;
		this.m = m;
	}

	public String toString() {
		return "从 " + n + " 个不同元素中取出 " + m + " 个元素，有 " + this.getA() + " 种排列。";
	}

	/**
	 * 计算排列数
	 * 
	 * An-m = n(n-1)(n-2)...(n-m+1) = n! / (n-m)!
	 * 
	 * @return 排列数
	 */
	public BigDecimal getA() {
		if (this.n == 0) {
			return new BigDecimal(1);
		}
		if (this.n == this.m) {
			return PC.Jie(n);
		}
		return PC.Jie(n).divide(PC.Jie(n - m));
	}

}
