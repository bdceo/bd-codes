package com.bdsoft.bdceo.j2se.sys;

import java.util.Map.Entry;

public class SystemInfo {

	public static void main(String[] args) {
		// 系统环境变量
		System.out.println("系统环境变量：");
		for (Entry<String, String> env : System.getenv().entrySet()) {
			System.out.println(env.getKey() + "=" + env.getValue());
		}

		// 系统属性
		System.out.println("\n\n系统属性：");
		for (Entry<Object, Object> enp : System.getProperties().entrySet()) {
			System.out.println(enp.getKey() + "=" + enp.getValue());
		}
	}

}
