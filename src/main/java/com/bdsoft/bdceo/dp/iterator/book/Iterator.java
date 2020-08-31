package com.bdsoft.bdceo.dp.iterator.book;

/**
 * 迭代器自身行为定义
 */
public interface Iterator {

    /**
     * 是否有下一个元素
     */
    boolean hasNext();

    /**
     * 下一个元素
     */
    Object next();
}
