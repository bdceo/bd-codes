package com.bdsoft.bdceo.jvm.oom;

/**
 * VM Args: -Xss128k
 * @author bdceo
 *
 */
public class JVMStackSOF {

	private int stackLength = 1;

	public void stackLeak() {
		stackLength++;
		stackLeak();
	}

	public static void main(String[] args) throws Throwable {
		JVMStackSOF test = new JVMStackSOF();

		try {
			test.stackLeak();
		} catch (Throwable e) {
			System.out.println("stack length :" + test.stackLength);
			throw e;
		}
	}

}
