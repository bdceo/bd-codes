package com.bdsoft.bdceo.dp.proxy.redis;

/**
 * @author 丁辰叶
 * @date 2015-11-27
 */
public interface Tedis {

    void get(String key);

    void set(String key, Object obj);
}
