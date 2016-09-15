package com.bdsoft.bdceo.dp.state.player;

public class Player {

	private State state = new NormalState();

	public void setState(State state) {
		this.state = state;
	}

	public void shot() {
		state.shot();
	}

	/**
	 * 
	 * 使用状态模式的好处是每个状态被封装到一个独立的类中，
	 * 
	 * 这些类可以独立变化，而主对象中没有繁琐的swich-case语句，
	 * 
	 * 并且添加新的状态非常容易，只需要从State派生一个新类即可。
	 * 
	 */
	public static void main(String[] aresg) {
		Player p = new Player();
		p.shot();

		p.setState(new NonormalState());
		p.shot();

		p.setState(new SuperState());
		p.shot();
	}

}
