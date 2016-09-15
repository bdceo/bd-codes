/**
 * IDuty.java
 * com.bdsoft.bdceo.dp.dutylist
 * Copyright (c) 2016, 北京微课创景教育科技有限公司版权所有.
*/

package com.bdsoft.bdceo.dp.dutylist;

/**
 * 责任链实现
 * 
 * @author   丁辰叶
 * @date	 2016-7-25
 * @version  1.0.0
 */
public interface IDuty {

	public String next(Integer level);

}
