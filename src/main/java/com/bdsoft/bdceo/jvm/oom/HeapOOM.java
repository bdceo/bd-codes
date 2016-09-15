package com.bdsoft.bdceo.jvm.oom;

import java.util.ArrayList;
import java.util.List;


/**
 * VM Args: -Xms2m -Xmx2m -XX:+HeapDumpOnOutOfMemoryError
 * @author bdceo
 *
 */
public class HeapOOM {

	static class OOMObject {
	}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();

		while (true) {
			list.add(new OOMObject());
		}
	}

}
