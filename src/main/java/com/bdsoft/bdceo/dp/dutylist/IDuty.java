package com.bdsoft.bdceo.dp.dutylist;

/**
 * 责任链实现
 */
public interface IDuty {

	String next(Integer level);

}
