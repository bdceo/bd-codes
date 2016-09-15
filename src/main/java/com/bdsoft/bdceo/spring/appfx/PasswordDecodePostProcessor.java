package com.bdsoft.bdceo.spring.appfx;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

// bean实例化中对bean增强
public class PasswordDecodePostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object obj, String str)
			throws BeansException {
		if (obj instanceof PasswordDecodable) {
			System.out.println("BeanPostProcessor-->");
			System.out
					.println("PasswordDecodePostProcessor-->postProcessBeforeInitialization()");
			String encp = ((PasswordDecodable) obj).getEncodedPassword();
			String decp = decodePassword(encp);
			((PasswordDecodable) obj).setDecodedPassword(decp);
			System.out.println("BeanPostProcessor<--");
		}
		return obj;
	}

	private String decodePassword(String encp) {
		// TODO 解码逻辑
		encp = ">>" + encp + "<<";
		return encp;
	}

}
