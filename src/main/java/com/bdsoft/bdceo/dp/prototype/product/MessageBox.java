package com.bdsoft.bdceo.dp.prototype.product;

import lombok.extern.slf4j.Slf4j;

/**
 * 具体的原型：消息箱
 */
@Slf4j
public class MessageBox implements Product {

    private String decoChar;

    public MessageBox(String decoChar) {
        this.decoChar = decoChar;
    }

    @Override
    public void use(String s) {
        int len = s.length();
        this.print(len);
        System.out.println();
        System.out.println(decoChar + " " + s + " " + decoChar);
        this.print(len);
        System.out.println();
        System.out.println();
    }

    private void print(int len) {
        for (int i = 0; i < len + 4; i++) {
            System.out.print(this.decoChar);
        }
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
