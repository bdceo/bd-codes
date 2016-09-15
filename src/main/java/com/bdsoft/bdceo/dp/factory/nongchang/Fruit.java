package com.bdsoft.bdceo.dp.factory.nongchang;

public interface Fruit {

}

/**
 * @author bdceo Email:bdceo@qq.com
 * 
 *         亚热带水果
 */
class NorthernFruit implements Fruit {

	private String name;

	public NorthernFruit(String name) {
		System.out.println("亚热带工厂为您创建了：亚热带水果－" + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

/**
 * 
 * @author bdceo Email:bdceo@qq.com
 * 
 *         热带水果
 */
class TropicalFruit implements Fruit {

	private String name;

	public TropicalFruit(String name) {
		System.out.println("热带工厂为您创建了：热带水果－" + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}