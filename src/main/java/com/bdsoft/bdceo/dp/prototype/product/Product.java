package com.bdsoft.bdceo.dp.prototype.product;

/**
 * 定义原型
 */
public interface Product extends Cloneable {

    /**
     * 使用
     */
    void use(String s);

    /**
     * 创建副本
     */
    Product createClone();

}
