package com.bdsoft.bdceo.dp.observer;

import java.util.Enumeration;
import java.util.Vector;

public class CustomerObserver {

	public static void main(String[] args) {
		Customer cus = new Customer();

		// 实例化观察者对象，自动注册到被观察者
		new AddrVerification();
		new WelcomeLetter();

		// 发出通知
		cus.notifyObs();
	}

}

class Customer {

	static private Vector<MyObserver> myObs;
	
	static {
		myObs = new Vector<>();
	}

	public Customer() {
	}

	// 注册
	public static void attach(MyObserver obs) {
		myObs.add(obs);
	}

	// 解绑
	public static void detach(MyObserver obs) {
		myObs.remove(obs);
	}

	// 事件属性
	public String getState() {
		return System.currentTimeMillis() + "";
	}

	// 通知
	public void notifyObs() {
		for (Enumeration<MyObserver> e = myObs.elements(); e.hasMoreElements();) {
			e.nextElement().update(this);
		}
	}
}

interface MyObserver {
	void update(Customer cus);
}

class AddrVerification implements MyObserver {

	// 观察者，自行负责注册到被观察对象上
	public AddrVerification() {
		Customer.attach(this);
	}

	public void update(Customer cus) {
		System.out.println("AddrVerification");
	}
}

class WelcomeLetter implements MyObserver {

	public WelcomeLetter() {
		Customer.attach(this);
	}

	public void update(Customer cus) {
		System.out.println("WelcomeLetter");
	}
}
