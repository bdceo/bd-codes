package com.bdsoft.bdceo.dp.prototype.product;

import lombok.extern.slf4j.Slf4j;

/**
 * 具体的原型：下划线
 */
@Slf4j
public class UnderLinePen implements Product {

    private String ulChar;

    public UnderLinePen(String ulChar) {
        this.ulChar = ulChar;
    }

    @Override
    public void use(String s) {
        int length = s.getBytes().length;
        System.out.println("\"" + s + "\"");
        System.out.print(" ");
        for (int i = 0; i < length; i++) {
            System.out.print(ulChar);
        }
        System.out.println();
        System.out.println();
    }

    @Override
    public Product createClone() {
        try {
            return (Product) clone();
        } catch (CloneNotSupportedException e) {
            log.error("克隆失败：", e);
            return null;
        }
    }
}
