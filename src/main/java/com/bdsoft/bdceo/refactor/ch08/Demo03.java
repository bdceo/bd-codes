package com.bdsoft.bdceo.refactor.ch08;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * 引用对象替换值对象
 */
public class Demo03 {

	/**
	 * 重构前，原customer属性，变为一个值对象
	 */
	static class Order {
		// 每个 OrderPlus 对象都包含自己的一个 Customer 对象
		private Customer customer; // 值对象，是不可变的

		public Order(String customerName) {
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
			if (each.getCustomerName().equals(customer)) {
				result++;
			}
		}
		return result;
	}

	/**
	 * 重构前：值对象
	 */
	static class Customer {
		// final，值对象创建后，不可修改
		final String name;

		// 通过构造函数设值
		public Customer(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

	/**
	 * 重构后：
	 */
	static class OrderPlus {

		private CustomerPlus customer; // 引用对象

		public OrderPlus(String customerName) {
			this.customer = CustomerPlus.getNamed(customerName);
		}

		public String getCustomerName() {
			return customer.getName();
		}

	}

	/**
	 * 重构后：
	 */
	static class CustomerPlus {

		private String name;

		// 对象注册表
		private static Dictionary instances = new Hashtable();

		// 预先加载
		static void loadCustomers() {
			new CustomerPlus("bdceo").store();
			new CustomerPlus("bdcoo").store();
			new CustomerPlus("bdcfo").store();
		}

		private void store() {
			instances.put(this.getName(), this);
		}

		// 工厂方法
		public static CustomerPlus getNamed(String name) {
			return (CustomerPlus) instances.get(name);
		}

		// 构造函数私有化
		private CustomerPlus(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

}
