package com.bdsoft.bdceo.java8.commonpo;

/**
 * 菜品
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/4/23 18:09
 */
public class Dish {

    private String name;
    private boolean vegetarian;
    private int calories;
    private Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public Dish() {
    }

    public Dish(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    public enum Type {MEAT, FISH, OTHER}

    public CaloricLevel getCaloricLevel() {
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

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
