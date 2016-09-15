package com.bdsoft.bdceo.refactor.ch08;

import java.util.Collection;
import java.util.Iterator;

/**
 * 以值对象取代数据值
 */
public class Demo02 {

	/**
	 * 重构前使用Order类
	 * 
	 * @param orders
	 * @param customer
	 * @return
	 */
	public static int numberOfOrdersFor(Collection orders, String customer) {
		int result = 0;
		Iterator ite = orders.iterator();
		while (ite.hasNext()) {
			Order each = (Order) ite.next();
			if (each.getCustomer().equals(customer)) {
				result++;
			}
		}
		return result;
	}

	/**
	 * 重构前，customer是个属性值
	 */
	static class Order {

		private String customer; // 数据值

		public Order(String customer) {
			this.customer = customer;
		}

		public String getCustomer() {
			return customer;
		}

		public void setCustomer(String customer) {
			this.customer = customer;
		}

	}

	/**
	 * 重构后，原customer属性，变为一个值对象
	 */
	static class OrderPlus {

		// 每个 OrderPlus 对象都包含自己的一个 Customer 对象
		private Customer customer; // 值对象，是不可变的

		public OrderPlus(String customerName) {
			this.customer = new Customer(customerName);
		}

		public String getCustomerName() {
			return customer.getName();
		}

		public void setCustomer(String customerName) {
			this.customer = new Customer(customerName);
		}
	}

	/**
	 * 重构后使用OrderPlus对象
	 * 
	 * @param orders
	 * @param customer
	 * @return
	 */
	public static int numberOfOrdersForPlus(Collection orders, String customer) {
		int result = 0;
		Iterator ite = orders.iterator();
		while (ite.hasNext()) {
			OrderPlus each = (OrderPlus) ite.next();
			if (each.getCustomerName().equals(customer)) {
				result++;
			}
		}
		return result;
	}

	/**
	 * 值对象
	 */
	static class Customer {
		// final，对象创建后，不可修改
		final String name;

		// 通过构造函数设值
		public Customer(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

}
