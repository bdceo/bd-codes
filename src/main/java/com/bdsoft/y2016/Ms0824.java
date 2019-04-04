package com.bdsoft.y2016;

public class Ms0824 {

	public static void main(String[] args) {
		
		// 比较相等：false
		System.out.println(3*0.1==0.3); // false，双等，比较的是内存地址，而非结果内容
		System.out.println(3*0.1); //   0.30000000000000004
		System.out.println(1.1+2.2); // 3.3000000000000003
		
		float f = 3 * 0.1f;// 默认乘以0.1结果将是double类型，所以0.1后要加f，结果才会是float
		double d = 0.3;// 默认小数声明都是double类型
		System.out.println("flaot="+f+", double="+d);
		System.out.println((float)d == f);

		
	}

}
