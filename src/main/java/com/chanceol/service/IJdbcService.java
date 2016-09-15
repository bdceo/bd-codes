package com.chanceol.service;

import javax.servlet.http.HttpServletRequest;

/**
 * service顶层抽象
 * 
 * @author 丁辰叶
 */
public interface IJdbcService {

	// 绑定前台request
	public void setRequest(HttpServletRequest req);

	// 绑定前台某个返回
	public void setBean(Object bean);

	// 实际业务处理
	public Object execute();
}
