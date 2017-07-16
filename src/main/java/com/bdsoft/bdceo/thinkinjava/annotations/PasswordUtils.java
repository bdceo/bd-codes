/**
 * PasswordUtils.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.annotations;

import java.util.List;

/**
 * PasswordUtils
 * 
 * @author bdceo
 * @date 2017-1-3 下午4:28:12
 * @version V1.0
 */
public class PasswordUtils {

	@UseCase(id = 47, info = "至少包含一个数字")
	public boolean validatePassword(String password) {
		return (password.matches("\\w*\\d\\w*"));
	}

	@UseCase(id = 48)
	public String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}

	@UseCase(id = 49, info = "新密码不能与使用过的密码相同")
	public boolean checkForNewPassword(List<String> prevPasswords, String password) {
		return prevPasswords.contains(password);
	}

}
