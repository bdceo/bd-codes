package com.bdsoft.bdceo.spring.jdbc.pattern.templatemethod;

public class VehicleAT extends Vehicle {

	@Override
	protected void putIntoGear() {
		System.out.println("自动挡，直接挂档前进");
	}

}
