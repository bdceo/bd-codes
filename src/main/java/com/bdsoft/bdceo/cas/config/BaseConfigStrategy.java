package com.bdsoft.bdceo.cas.config;

import org.springframework.util.StringUtils;

/**
 * 基本的配置策略实现
 * 
 * @author bdceo
 *
 */
public abstract class BaseConfigStrategy implements ConfigStrategy {

	private interface Parser<T> {
		T parse(String value);
	}

	/**
	 * 默认返回String
	 * @param key
	 * @return
	 */
	protected abstract String get(ConfigKey key);

	private <T> T getValue(ConfigKey<T> key, Parser<T> parser) {
		String value = get(key);
		if (StringUtils.isEmpty(value)) {
			return key.getDefaultValue();
		}
		return parser.parse(value);
	}

	@Override
	public boolean getBoolean(ConfigKey<Boolean> key) {
		return getValue(key, new Parser<Boolean>() {
			public Boolean parse(String value) {
				return Boolean.valueOf(value);
			}
		});
	}

	@Override
	public String getString(ConfigKey<String> key) {
		return getValue(key, new Parser<String>() {
			public String parse(String value) {
				return value;
			}
		});
	}

	@Override
	public long getLong(ConfigKey<Long> key) {
		return getValue(key, new Parser<Long>() {
			public Long parse(String value) {
				return Long.parseLong(value);
			}
		});
	}

	@Override
	public int getInt(ConfigKey<Integer> key) {
		return getValue(key, new Parser<Integer>() {
			public Integer parse(String value) {
				return Integer.parseInt(value);
			}
		});
	}

}
