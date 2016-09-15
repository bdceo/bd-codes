package com.bdsoft.y2012.m01;

import java.math.BigDecimal;

/**
 * 组合
 * 
 * @author bdceo
 * 
 */
public class C {
	
	private int n;// 总元素数
	private int m;// 取出元素个数(m<=n)，一个组合

	public String toString() {
		return "从 " + n + " 个不同元素中取出 " + m + " 个元素，有 " + this.getC() + " 种组合。";
	}

	public C() {
	}

	public C(int n, int m) {
		this.n = n;
		this.m = m;
	}

	/**
	 * 计算组合数
	 * 
	 * Cn-m = Pn-m / m! = n! / (n-m)!m!
	 * 
	 * @return 组合数
	 */
	public BigDecimal getC() {
		if (m == 0) {
			return new BigDecimal(1);
		}
		P p1 = new P(n, m);
		return p1.getA().divide(PC.Jie(m));
	}
}
