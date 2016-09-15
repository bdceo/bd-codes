/**
 * JarUtil.java
 * com.bdsoft.bdceo.ibatis.src
 * Copyright (c) 2016, 北京微课创景教育科技有限公司版权所有.
*/

package com.bdsoft.bdceo.ibatis.src;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import com.google.common.collect.Lists;

/**
 * mybatis注册类型别名处理时，找jar中资源方法测试
 * 
 * 参考MyBatis源码：TypeAliasRegistry.registerAliases()-ResolverUtil.find()
 *
 * @author   丁辰叶
 * @date	 2016-6-22
 * @version  1.0.0
 */

public class JarUtil {

	// jar文件头
	private static final byte[] JAR_MAGIC = { 'P', 'K', 3, 4 };

	public static void main(String[] args) throws Exception {
		findJar("org.joda.time.base");
	}

	/**
	 * 通过报名，找jar
	 *
	 * @throws Exception
	 */
	public static void findJar(String pack) throws Exception {
		System.out.println("指定包名查找相关Jar：");
		pack = pack.replace(".", "/");
		Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(pack);
		List<URL> urlList = Collections.list(urls);
		URL jurl = null;
		for (URL url : urlList) {
			System.out.println("发现>" + url);
			try {
				for (;;) {
					url = new URL(url.getFile());
				}
			} catch (MalformedURLException e) {
				System.err.println(e.getMessage());
			}
			jurl = url;
		}

		StringBuilder sb = new StringBuilder(jurl.toExternalForm());
		int index = sb.lastIndexOf(".jar");
		if (index >= 0) {
			sb.setLength(index + 4);
			jurl = new URL(sb.toString());
			System.out.println("是jar>" + isJar(jurl));

			listResources(new JarInputStream(jurl.openStream()), pack);
		} else {
			System.out.println("不是个jar");
		}
	}

	// 遍历jar中的资源
	public static List<String> listResources(JarInputStream jis, String path) throws Exception {
		List<String> res = Lists.newArrayList();

		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		if (!path.endsWith("/")) {
			path = path + "/";
		}
		System.out.println(path);

		for (JarEntry entry; (entry = jis.getNextJarEntry()) != null;) {
			if (!entry.isDirectory()) {
				String name = entry.getName();
				System.out.println("jar中资源>" + name);
				if (!name.startsWith("/")) {
					name = "/" + name;
				}
				if (name.startsWith(path)) {
					System.out.println("jar中指定包资源>" + name);
					res.add(name.substring(1));
				}
			}
		}
		return res;
	}

	// 判断文件头，是否为jar
	public static boolean isJar(URL url) {
		return isJar(url, new byte[JAR_MAGIC.length]);
	}

	public static boolean isJar(URL url, byte[] buffer) {
		InputStream is = null;
		try {
			is = url.openStream();
			is.read(buffer, 0, JAR_MAGIC.length);
			if (Arrays.equals(buffer, JAR_MAGIC)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return false;
	}
}
