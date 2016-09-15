package com.bdsoft.bjsxt.dp.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		// Child c = new Child();
		// Dad d = new Dad(c);
		// new Thread(d).start();

		// Dad d = new Dad();
		// Child c = new Child(d);
		// new Thread(c).start();

		// Dog g = new Dog();
		// Dad d = new Dad();
		// GrandFather gf = new GrandFather();
		// Child c = new Child();
		// c.addWakeUpListener(d);
		// c.addWakeUpListener(gf);
		// c.addWakeUpListener(g);
		// c.addCryListener(d);
		// c.addCryListener(g);
		// new Thread(c).start();

		// Child c = new Child();
		// Properties props = new Properties();
		// try {
		// props.load(Test.class.getClassLoader().getResourceAsStream(
		// "observer.properties"));
		// String objs = props.getProperty("observer");
		// for (String str : (objs.split(","))) {
		// try {
		// c.addWakeUpListener((WakeUpListener) (Class.forName(str)
		// .newInstance()));
		// } catch (Exception se) {
		// se.printStackTrace();
		// }
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// new Thread(c).start();

		Child c = new Child();
		PropertyMgr propertyMgr = PropertyMgr.getInstance();
		String objs = propertyMgr.getProperty("observer");
		for (String s : objs.split(",")) {
			try {
				c.addWakeUpListener((WakeUpListener) (Class.forName(s)
						.newInstance()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		new Thread(c).start();
	}
}

interface WakeUpListener {
	public void ActionToWakenUp(WakeUpEvent e);
}

class Dad implements WakeUpListener {
	public void ActionToWakenUp(WakeUpEvent e) {
		System.out.println("feed child and father know it--" + e.getTime());
	}
}

class GrandFather implements WakeUpListener {
	public void ActionToWakenUp(WakeUpEvent e) {
		System.out.println("hug child and grandFather know it--" + e.getTime());
	}
}

class Dog implements WakeUpListener {
	public void ActionToWakenUp(WakeUpEvent e) {
		System.out.println("play with child and dog know it--" + e.getTime());
	}
}

class Child implements Runnable {
	private List<WakeUpListener> wakenUpListeners = new ArrayList<WakeUpListener>();

	public void addWakeUpListener(WakeUpListener wk) {
		this.wakenUpListeners.add(wk);
	}

	public void wakeUp() {
		for (Iterator iterator = wakenUpListeners.iterator(); iterator
				.hasNext();) {
			WakeUpListener wul = (WakeUpListener) iterator.next();
			wul.ActionToWakenUp(new WakeUpEvent(System.currentTimeMillis(),
					"bed", this));
		}
	}

	public void run() {
		try {
			Thread.sleep(1409);
			wakeUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
