package com.bdsoft.gkh.factory;

public class Factory1 {
	public static void main(String[] args) {
	}
}

// 1：面向接口编程
// 2：建立工厂类
class Action {
	public void callByDao() {
		// CustomerDao dao = new CustomerDao();
		ICustomerDao dao = (ICustomerDao) DaoFactory.getDao("Customer");
		dao.login();
	}
}

class DaoFactory {
	// public static ICustomerDao getDao(String name)
	public static Object getDao(String name) {
		// if (name.equals("Customer")) {
		// 预先准备工作
		// 根据配置文件获取DaoName对应的类名
		String daoName = name;
		try {
			return Class.forName(daoName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return new CustomerDao();
		// } else if (true/**/) {
		return null;
	}
}// ------------------------------>配置文件：Spring

interface ICustomerDao {
	public void login();
}

class CustomerDao implements ICustomerDao {
	public void login() {
		System.out.println("Dao login");
	}
}