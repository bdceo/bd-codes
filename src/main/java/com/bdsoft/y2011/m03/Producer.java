package com.bdsoft.y2011.m03;

public class Producer extends Thread {

	private int produceNum;
	private BreadContainer bc;

	public Producer() {
	}

	public Producer(int produceNum, BreadContainer bc, String producerName) {
		this.produceNum = produceNum;
		this.bc = bc;
		this.setName(producerName);
	}

	public void run() {
		bc.produceBread(produceNum, this.getName());
	}
}

class Consumer extends Thread {
	private int consumeNum;
	private BreadContainer bc;

	public Consumer() {
	}

	public Consumer(int consumeNum, BreadContainer bc, String consumeName) {
		this.consumeNum = consumeNum;
		this.bc = bc;
		this.setName(consumeName);
	}

	public void run() {
		bc.consumeBread(consumeNum, this.getName());
	}
}
