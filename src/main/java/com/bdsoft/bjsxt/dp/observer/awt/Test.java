package com.bdsoft.bjsxt.dp.observer.awt;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		Button b = new Button();
		b.addActionListeners(new MyActionListener());
		b.buttonPressed();
	}
}

class Button {
	private List<ActionListener> actionListeners = new ArrayList<ActionListener>();

	public void buttonPressed() {
		ActionEvent e = new ActionEvent(System.currentTimeMillis(), this);
		for (int i = 0; i < actionListeners.size(); i++) {
			ActionListener al = actionListeners.get(i);
			al.actionperformed(e);
		}
	}

	public void addActionListeners(ActionListener al) {
		this.actionListeners.add(al);
	}
}

interface ActionListener {
	public void actionperformed(ActionEvent e);
}

class MyActionListener implements ActionListener {
	public void actionperformed(ActionEvent e) {
		System.out
				.println("button pressed..." + e.when + "..." + e.getSource());
	}
}

class ActionEvent {
	long when;
	Object source;

	public ActionEvent(long when, Object s) {
		this.when = when;
		this.source = s;
	}

	public Object getSource() {
		return source;
	}

	public long getWhen() {
		return this.when;
	}
}