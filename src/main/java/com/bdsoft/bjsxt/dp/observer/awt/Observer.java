package com.bdsoft.bjsxt.dp.observer.awt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Observer {

	// 观察者模式
	public static void main(String[] aw) {
		Input i = new Input();
		i.addEvtLis(new MyListener());
		i.inputBlur();
	}
}

class Input {
	private List<Listener> lis = new ArrayList<Listener>();

	public void inputBlur() {
		MyEvent e = new MyEvent();
		for (int i = 0; i < lis.size(); i++) {
			Listener listener = lis.get(i);
			listener.actionExecetu(e);
		}
	}

	// 添加事件监听器
	public void addEvtLis(Listener l) {
		lis.add(l);
	}
}

interface Listener {
	public void actionExecetu(MyEvent e);
}

class MyListener implements Listener {
	public void actionExecetu(MyEvent e) {
		System.out.println(e.getDate().toLocaleString());
	}
}

class MyEvent {
	private Date date;

	public MyEvent() {
		this.date = new Date();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
