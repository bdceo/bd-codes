package com.bdsoft.bdceo.dp.factory.nongchang;

// 抽象工厂
public interface Gardener {

	public Fruit createFruit(String name);

	public Veggie createVeggie(String name);
}

// 热带工厂，创建亚热带水果和蔬菜
class NorthernGardener implements Gardener {

	public Fruit createFruit(String name) {
		return new NorthernFruit(name);
	}

	public Veggie createVeggie(String name) {
		return new NorthernVeggie(name);
	}

}

// 亚热带工厂，创建热带水果和蔬菜
class TropicalGardener implements Gardener {

	public Fruit createFruit(String name) {
		return new TropicalFruit(name);
	}

	public Veggie createVeggie(String name) {
		return new TropicalVeggie(name);
	}

}
