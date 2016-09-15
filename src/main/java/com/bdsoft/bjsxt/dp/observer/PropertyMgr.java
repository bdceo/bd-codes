package com.bdsoft.bjsxt.dp.observer;

import java.util.Properties;

//读取属性文件工具类
public class PropertyMgr {
	private static Properties props = new Properties();
	private static PropertyMgr propertyMgr = new PropertyMgr();
	static {
		try {
			props.load(Test.class.getClassLoader().getResourceAsStream(
					"observer.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PropertyMgr() {
	}

	public static PropertyMgr getInstance() {
		if (propertyMgr == null) {
			propertyMgr = new PropertyMgr();
		}
		return propertyMgr;
	}

	public Properties getProperties() {
		if (props == null) {
			props = new Properties();
		}
		return props;
	}

	public String getProperty(String key) {
		return props.getProperty(key);
	}
}
