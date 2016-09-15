package com.bdsoft.bdceo.dp.bridge.car;

public interface CarEngine {
	public void setEngine();
}

class Engigeof1500CC implements CarEngine {
	public void setEngine() {
		System.out.println("1500cc");
	}
}

class Engigeof2000CC implements CarEngine {
	public void setEngine() {
		System.out.println("2000cc");
	}
}
