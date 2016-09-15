package com.bdsoft.y2012.m12;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) {

		// 死循环
		// for(;;){
		// System.out.println(new Date().toLocaleString());
		// }

		Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("出版社", "邮电");
		infoMap.put("作者", "老丁");
		for (Entry<String, String> en : infoMap.entrySet()) {
			System.out.println(en.getKey() + ":" + en.getValue());
			System.out.println(infoMap.get(en.getKey()));
		}

		String str = "http://book.douban.com/people/50263697/";
		str = str.substring(str.indexOf("com") + 3);
		System.out.println(str);
	}

}
