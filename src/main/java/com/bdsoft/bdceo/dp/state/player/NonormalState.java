package com.bdsoft.bdceo.dp.state.player;

public class NonormalState implements State {

	@Override
	public void shot() {
		System.out.println("今天你投篮十中一");
	}

}
