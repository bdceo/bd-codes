package com.bdsoft.bdceo.dp.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Jdk版观察者模式实现
 * 
 * 目标被观察者对象需要继承Observalbe对象
 * 
 * 观察者实现类需要实现Observer接口
 * 
 * 当目标对象发生变化时，需要调用setChanged方法
 *
 */
public class JdkOberver {

	public static void main(String[] args) {
		Subject sub = new Subject();

		// 两种注册监听方式：注册观察者
		sub.addObserver(new TwoHandler());
		sub.reg(new OneHandler());

		sub.alert();

		System.out.println();

		sub.error();

	}

}

/**
 * 被观察者，继承自：Observable，辅助实现了【注册/删除/通知】观察者的方法
 * 
 * 其中，在通知观察者时，需要先调用setChange方法，标明被观察者发生了变化
 * 然后，通知观察者notifyObservers方法，再确认所有观察者被执行完毕后可以将变化标识重置
 *
 * @author   丁辰叶
 * @date	 2016-7-25
 * @version  1.0.0
 */
class Subject extends Observable {

	public Subject() {
		super();
	}

	public void reg(Observer obs) {
		super.addObserver(obs);
	}

	public void alert() {
		// 关键行，需要设置发生了变化，当所有观察者处理完自己的业务
		this.setChanged();
		super.notifyObservers("alert");
	}

	public void error() {
		this.setChanged();
		super.notifyObservers("error");
	}
}

/**
 * 观察者，需要实现统一接口：Observer，行为统一，即处理被观察者发来的通知内容
 */
class OneHandler implements Observer {

	public void update(Observable obs, Object param) {
		System.out.println("OneHandler -> " + (String) param);

		if (param instanceof String) {
		}

		System.out.println(obs.getClass());
		((Subject) obs).deleteObserver(this);
	}

}

class TwoHandler implements Observer {

	public void update(Observable subject, Object param) {
		System.out.println("TwoHandler -> " + (String) param);
	}

}
