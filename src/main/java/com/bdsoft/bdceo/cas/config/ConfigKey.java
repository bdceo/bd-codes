package com.bdsoft.bdceo.cas.config;

/**
 * 配置Key
 * @author bdceo
 *
 * @param <T>
 */
public class ConfigKey<T> {

	private String name;
	private T defaultValue;

	public ConfigKey(String name) {
		this.name = name;
	}
	
	public ConfigKey(String name, T defaultValue){
		this.name = name;
		this.defaultValue = defaultValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}

}