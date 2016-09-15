package com.bdsoft.bdceo.dp.factory.nongchang;

public interface Veggie {

}

/**
 * 
 * @author bdceo Email:bdceo@qq.com
 * 
 *         热带蔬菜
 */
class TropicalVeggie implements Veggie {

	private String name;

	public TropicalVeggie(String name) {
		System.out.println("热带工厂为您创建了：热带蔬菜－" + name);
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
 *         亚热带蔬菜
 */
class NorthernVeggie implements Veggie {

	private String name;

	public NorthernVeggie(String name) {
		System.out.println("亚热带工厂为您创建了：亚热带蔬菜－" + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
