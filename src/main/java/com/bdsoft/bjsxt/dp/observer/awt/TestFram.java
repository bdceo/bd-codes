package com.bdsoft.bjsxt.dp.observer.awt;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestFram extends Frame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new TestFram().launch();
	}

	public void launch() {
		Button b = new Button("Press me");
		b.addActionListener(new MyActionListener1());
		b.addActionListener(new MyActionListener2());
		this.add(b);
		this.pack();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(1);
			}
		});
		this.setVisible(true);
	}

	private class MyActionListener1 implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("button has clicked--1!");
		}
	}

	private class MyActionListener2 implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("button has clicked--2!");
		}
	}
}
