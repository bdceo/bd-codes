package com.bdsoft.bdceo.dp.strategy.duck;

public interface IFlyBehavior {
	public String fly();
}

class FlyWithWings implements IFlyBehavior {

	public String fly() {
		return "I will fly high ...";
	}
}

class FlyNoWay implements IFlyBehavior {

	public String fly() {
		return "I can not fly ...";
	}
}
