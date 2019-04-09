package com.bdsoft.bdceo.dp.dutylist;

import java.util.Random;

/**
 * 责任链模式 
 *
 * @author   丁辰叶
 * @date	 2016-7-25
 * @version  1.0.0
 */
public class TestMain {

	public static void main(String[] args) {

		IDuty id = new Level1Duty();

		for (int i = 0; i < 10; i++) {
			int d = new Random().nextInt(10);
			System.out.print("今天是周" + d + "如果买彩票的话，" + id.next(d) + "\n");
		}

	}

}
