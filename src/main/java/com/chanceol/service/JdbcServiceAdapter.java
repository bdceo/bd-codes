package com.chanceol.service;

import javax.servlet.http.HttpServletRequest;

/**
 * service层适配器，可以实现事务管理
 * 真正子类需要实现doExecute方法
 * 
 * @author 丁辰叶
 */
public abstract class JdbcServiceAdapter implements IJdbcService {

	private Object bean;
	private HttpServletRequest req;

	// 绑定前台request
	public void setRequest(HttpServletRequest req) {
		this.req = req;
	}

	// 绑定前台某个返回
	public void setBean(Object bean) {
		this.bean = bean;
	}

	// 实际业务处理
	public Object execute() {
		Object data = null;
		try {
			// 获取数据库连接
			data = doExecute();
			// 提交事务
			return data;
		} catch (Exception e) {
			// 回滚
		} finally {
			// 关闭
		}
		return data;
	}

	/**
	 * 真正业务层需要实现的方法
	 * 
	 * @return
	 */
	protected abstract Object doExecute();

	public HttpServletRequest getReq() {
		return req;
	}

	public void setReq(HttpServletRequest req) {
		this.req = req;
	}

	public Object getBean() {
		return bean;
	}

}
