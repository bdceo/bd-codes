package com.bdsoft.bdceo.spring.jdbc.pattern.templatemethod;

public class VehicleMT extends Vehicle {

	@Override
	protected void putIntoGear() {
		System.out.println("手动挡-踩离合，挂档");
	}

}
