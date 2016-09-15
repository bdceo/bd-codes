package com.bdsoft.bdceo.dp.factory.nongchang;

public class Main {

	// 抽象工厂演示
	public static void main(String[] a) {
		Veggie tv = null, nv = null;
		Fruit tf = null, nf = null;

		Gardener tg = new TropicalGardener();
		tv = tg.createVeggie("辣椒");
		tf = tg.createFruit("椰子");

		System.out.println("-------------------------");
		
		Gardener ng = new NorthernGardener();
		nv = ng.createVeggie("豆角");
		nf = ng.createFruit("雪梨");
	}
}
