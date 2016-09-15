/**
 * SpringPackageUtils.java
 * com.bdsoft.bdceo.spring.utils
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.spring.utils;

import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;

/**
 * Spring-工具包使用测试
 * 
 * @author	丁辰叶
 * @date	2015-11-24
 */
public class SpringPackageUtils {

	public static void main(String[] args) {
		// 将 propertis 转成 map
		Map map = Maps.newHashMap();
		CollectionUtils.mergePropertiesIntoMap(System.getProperties(), map);
		System.out.println(map);
		
		
	}

}
