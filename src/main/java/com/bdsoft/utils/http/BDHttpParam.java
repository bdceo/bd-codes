package com.bdsoft.utils.http;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 参数自定义传输封装
 */
public class BDHttpParam {

	// 通过cookie传输的参数名称定义
	public final static String COOKIE_TOKEN = "vkoweb";
	public final static String COOKIE_TERMINAL = "terminal";
	public final static String COOKIE_SID = "sid";

	// 通过http头自定义传输的参数名称定义
	public final static String HEADER_IP = "X-Forwarded-For";

	// 默认字符集
	private String charset = "utf-8";

	private Map<String, String> commonParams; // get/post 传参
	private Map<String, String> cookieParams; // cookie 传参
	private Map<String, String> headerParams; // header 传参

	/**
	 * 私有初始化
	 */
	private BDHttpParam() {
		commonParams = Maps.newHashMap();
		cookieParams = Maps.newHashMap();
		
		headerParams = Maps.newHashMap();
		headerParams.put("Accept-Encoding", "gzip");
		headerParams.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
	}

	/**
	 * 静态调用初始化
	 */
	public static BDHttpParam init() {
		return new BDHttpParam();
	}

	public boolean hasCommon() {
		return !getCommonParams().isEmpty();
	}

	public boolean hasCookie() {
		return !getCookieParams().isEmpty();
	}

	public boolean hasHeader() {
		return !getHeaderParams().isEmpty();
	}

	public BDHttpParam addCommon(String key, String value) {
		getCommonParams().put(key, value);
		return this;
	}

	public BDHttpParam addCookie(String key, String value) {
		getCookieParams().put(key, value);
		return this;
	}

	public BDHttpParam addHeader(String key, String value) {
		getHeaderParams().put(key, value);
		return this;
	}

	public BDHttpParam setCharset(String charset) {
		this.charset = charset;
		return this;
	}

	public Map<String, String> getCommonParams() {
		return commonParams;
	}

	public Map<String, String> getCookieParams() {
		return cookieParams;
	}

	public Map<String, String> getHeaderParams() {
		return headerParams;
	}

	public String getCharset() {
		return charset;
	}

}
