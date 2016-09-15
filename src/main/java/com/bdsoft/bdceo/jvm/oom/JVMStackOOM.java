package com.bdsoft.bdceo.jvm.oom;

/**
 * VM Args: -Xss1m
 * 
 * @author bdceo
 * 
 */
public class JVMStackOOM {

	private void dontStop() {
		while (true) {
		}
	}

	public void stackLeakByThread() {

		while (true) {
			new Thread() {
				public void run() {
					dontStop();
				}
			}.start();
		}
	}

	public static void main(String[] args) {
		JVMStackOOM test = new JVMStackOOM();
		test.stackLeakByThread();

	}

}
