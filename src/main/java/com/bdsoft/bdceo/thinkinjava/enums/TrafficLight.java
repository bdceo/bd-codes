/**
 * TrafficLight.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.enums;


/**
 * TrafficLight
 * 
 * @author bdceo
 * @date 2016-12-29 下午12:24:54
 * @version V1.0
 */
public class TrafficLight {

	Signal color = Signal.RED;

	public void change() {
		switch (color) {
		case RED:
			color = Signal.GREEN;
			break;
		case GREEN:
			color = Signal.YELLOW;
			break;
		case YELLOW:
			color = Signal.RED;
			break;
		}
	}
	
	public String toString(){
		return "当前是："+ color;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TrafficLight tl = new TrafficLight();
		for(int i=0;i<7;i++){
			System.out.println(tl);
			tl.change();
		}
	}

}

enum Signal {
	GREEN, YELLOW, RED
}
