/**
 * UseCaseTracker.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UseCaseTracker
 * 
 * @author bdceo
 * @date 2017-1-3 下午4:31:42
 * @version V1.0
 */
public class UseCaseTracker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> useCases = new ArrayList<Integer>();
		Collections.addAll(useCases, 49, 48, 45, 44);
		trackUseCases(useCases, PasswordUtils.class);
	}

	public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
		for (Method m : cl.getDeclaredMethods()) {
			UseCase uc = m.getAnnotation(UseCase.class);
			if (uc != null) {
				System.out.println(String.format("找到用例#%d %s", uc.id(), uc.info()));
				useCases.remove(new Integer(uc.id()));
			}
		}
		for (int i : useCases) {
			System.out.println("未找到用例#" + i);
		}
	}

}
