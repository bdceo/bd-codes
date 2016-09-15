package com.bdsoft.bdceo.j2se.jiekou;

public class InteTest {

	public static void main(String[] args) {
		Moniter m = new Moniter();
		Listener la = new ProcessA();
		m.registListener(la);
		m.execute();

		Listener lb = new ProcessB();
		m.registListener(lb);
		m.execute();
	}

}

interface Listener {
	public void processEvent();
}

class Moniter {

	Listener lis;

	public void registListener(Listener l) {
		this.lis = l;
	}

	public void execute() {
		lis.processEvent();
	}
}

class ProcessA implements Listener {
	public void processEvent() {
		System.out.println("Process A execute...");
	}
}

class ProcessB implements Listener {
	public void processEvent() {
		System.out.println("Process B execute...");
	}
}
