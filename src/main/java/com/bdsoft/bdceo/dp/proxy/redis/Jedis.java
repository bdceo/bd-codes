/**
 * Jedis.java
 * com.bdsoft.bdceo.dp.proxy.redis
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.dp.proxy.redis;

/**
 * 
 * @author	丁辰叶
 * @date	2015-11-27
 */
public interface Jedis {
	
	public void get(String key);
	
	public void set(String key, Object obj);
	
}
