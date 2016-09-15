package com.bdsoft.bdceo.j2se.col;

public class ArrTest {

	public static void main(String[] args) {
		baseTest();
	}

	public static void baseTest() {
		// 创建数组时必须指定其大小长度
		// int[] ia1 = new int[];
		// 如果要指定数组的初始化内容，即加了大括号，则不能指定数组的大小
		// int[] ia1 = new int[4]{};
		int[] ia1 = new int[4];

		int[] ia2 = new int[] { 1, 2, 3 };
		// 数组创建时进行了初始化，则其大小已经固定，索引3位置的赋值操作将出错
		// ia2[3] = 4;
		
		
	}
}
