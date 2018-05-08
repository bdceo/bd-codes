package com.bdsoft.bdceo.java8.commonpo;

/**
 * 功能
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/5/8 9:39
 */
public class Trader {

    private String name;
    private String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
