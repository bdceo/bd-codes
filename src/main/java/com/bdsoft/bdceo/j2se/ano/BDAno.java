/**
 * BDAno.java
 * com.bdsoft.bdceo.j2se.ano
 * Copyright (c) 2016, 北京微课创景教育科技有限公司版权所有.
*/

package com.bdsoft.bdceo.j2se.ano;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * <p>
 *
 * @author   丁辰叶
 * @date	 2016-6-14
 * @version  1.0.0
 */
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
public @interface BDAno {

	public String name();

	public String desc();
}
