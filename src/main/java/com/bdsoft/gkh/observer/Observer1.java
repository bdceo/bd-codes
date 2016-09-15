package com.bdsoft.gkh.observer;

import java.util.Observable;
import java.util.Observer;

public class Observer1 {

	/**
	 * 观察者模式，使用jdk提供的接口开发
	 * 
	 * 观察者实现Observer接口，被观察者继承Observable类
	 */
	public static void main(String[] args) {
		// 观察者
		Customer cus = new Customer("bdceo");
		// 被观察者
		Product product = new Product("apple", 12);

		// 观察者和被观察者绑定，可以绑定多个关注着
		// product.addObserver(cus);
		product.setObserver(cus);

		product.changePrice(10);
	}

}

// 产品类，产品的价格变动要通知客户
// 目标：产品不关心通知哪些客户，客户不关心哪个产品发出的通知
// 产品：被观察者，客户：观察者
class Product extends Observable {
	private String pname;
	private double price;

	public Product(String name, double price) {
		this.pname = name;
		this.price = price;
	}

	// 方便spring注入
	public void setObserver(Observer obs) {
		this.addObserver(obs);
	}

	public void changePrice(double newPrice) {
		this.price = newPrice;
		// 通知观察者
		this.setChanged();// 设置变化点，通知观察者
		this.notifyObservers(newPrice);
	}
}

// 客户
class Customer implements Observer {
	private String cname;

	public Customer(String name) {
		this.cname = name;
	}

	public void printInf() {
		System.out.println(cname + " know the price changed!");
	}

	@Override
	// target:来源；arg：通知传来的参数值
	public void update(Observable src, Object arg) {
		// TODO Auto-generated method stub
		this.printInf();
		System.out.println("the new price = " + arg);
		System.out.println("change from " + src.toString());
	}
}
