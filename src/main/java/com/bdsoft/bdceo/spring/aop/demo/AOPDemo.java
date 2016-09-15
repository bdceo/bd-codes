package com.bdsoft.bdceo.spring.aop.demo;

import com.bdsoft.utils.StringUtil;

public class AOPDemo {

	public AOPDemo() {
	}

	public Object login(String username, String pwd) {
		System.out.println("AOPDemo-->login");
		if (StringUtil.isEmpty(username)) {
			int a = 1 / 0;
			a++;
		}
		return null;
	}

}
