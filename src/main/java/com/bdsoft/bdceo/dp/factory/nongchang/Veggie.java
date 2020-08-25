package com.bdsoft.bdceo.dp.factory.nongchang;

/**
 * 蔬菜类型
 */
public interface Veggie {
    String getName();
}

/**
 * 热带蔬菜
 */
class TropicalVeggie implements Veggie {

    private String name;

    public TropicalVeggie(String name) {
        System.out.println("热带工厂为您创建了：热带蔬菜－" + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

/**
 * 亚热带蔬菜
 */
class NorthernVeggie implements Veggie {

    private String name;

    public NorthernVeggie(String name) {
        System.out.println("亚热带工厂为您创建了：亚热带蔬菜－" + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
