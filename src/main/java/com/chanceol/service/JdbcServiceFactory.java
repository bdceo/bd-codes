package com.chanceol.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 暴漏给控制层的获取service实现的方法
 * 
 * @author 丁辰叶
 */
public class JdbcServiceFactory {

	public static IJdbcService getService(String className,
			HttpServletRequest req, Object bean) {
		IJdbcService service = null;

		try {
			service = (IJdbcService) Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("未找到类");
		}
		service.setRequest(req);
		service.setBean(bean);

		return service;
	}
}
