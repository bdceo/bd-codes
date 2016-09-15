package com.bdsoft.y2012.m01;

import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		Random rd = new Random();

		// 指定范围内的随机数，通过取余获得
		for (int i = 0; i < 2; i++) {
			int it = rd.nextInt();
			System.out.print(it);
			System.out.println("\t" + it % 100);
		}

		// 通过传递参数获得
		for (int i = 0; i < 10; i++) {
			System.out.println(rd.nextInt(4));
		}

	}

}
