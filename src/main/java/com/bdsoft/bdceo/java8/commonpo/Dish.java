package com.bdsoft.bdceo.java8.commonpo;

/**
 * 功能
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/4/23 18:09
 */
public class Dish {

    private String name;
    private int calories;

    public CaloricLevel getCaloricLevel(){
        if (this.getCalories() <= 400) {
            return CaloricLevel.DIET;
        } else if (this.getCalories() <= 700) {
            return CaloricLevel.NORMAL;
        } else {
            return CaloricLevel.FAT;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
