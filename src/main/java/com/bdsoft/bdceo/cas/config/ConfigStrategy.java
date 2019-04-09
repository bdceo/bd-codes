package com.bdsoft.bdceo.cas.config;

/**
 * 配置策略
 */
public interface ConfigStrategy {
	
	void init();

	boolean getBoolean(ConfigKey<Boolean> key);

	String getString(ConfigKey<String> key);

	long getLong(ConfigKey<Long> key);

	int getInt(ConfigKey<Integer> key);

}
