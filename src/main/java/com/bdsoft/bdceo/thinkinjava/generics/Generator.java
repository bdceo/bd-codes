/**
 * Generator.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.generics;

/**
 * 泛型生成器接口
 * 
 * @author bdceo
 * @date 2016-8-19 上午4:27:52
 * @version V1.0
 */
public interface Generator<T> {
	
	// 生成一个对象
	public T next();
}
