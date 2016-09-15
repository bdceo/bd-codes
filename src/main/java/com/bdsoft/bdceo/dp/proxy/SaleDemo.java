package com.bdsoft.bdceo.dp.proxy;

public class SaleDemo {

	public static void main(String[] args) {
		ISaleComputer sale = new ProxySale(new FactorySale());
		sale.sale();
	}

}

interface ISaleComputer {
	void sale();
}

// 代理商
class ProxySale implements ISaleComputer {

	private ISaleComputer sale;

	public ProxySale(ISaleComputer sale) {
		this.sale = sale;
	}

	public void sale() {
		System.out.println("Proxy sale 50%");
		sale.sale();
		System.out.println("Proxy sale out");
	}
}

// 生产厂商
class FactorySale implements ISaleComputer {
	public void sale() {
		System.out.println("Factory sale out");
	}
}
