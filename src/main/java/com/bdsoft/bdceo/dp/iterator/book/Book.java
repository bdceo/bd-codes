package com.bdsoft.bdceo.dp.iterator.book;

import lombok.Data;

/**
 * 基础对象：书本
 */
@Data
public class Book {

    private String name;

    public Book(String name) {
        this.name = name;
    }
}
