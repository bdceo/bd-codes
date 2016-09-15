package com.bdsoft.bdceo.datastruct.list;

/**
 * 链表：递归测试
 * 
 * @author bdceo
 * @date 2016-8-15 下午11:28:34
 */
public class BDList {

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		System.out.println(digui(5));
	}

	/**
	 * 内部确定递归退出条件，外部也要控制递归次数
	 * 
	 * @param i 数据
	 * @return
	 */
	static int digui(int i) {
		if (i == 1) {
			return 1;
		}
		return i * digui(i - 1);
	}

}
