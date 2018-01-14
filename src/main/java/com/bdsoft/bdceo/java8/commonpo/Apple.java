package com.bdsoft.bdceo.java8.commonpo;

/**
 * Created by bdceo on 2018/1/8.
 */
public class Apple {

    private int weight;
    private String color;

    public Apple() {
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public static boolean isGreenApple(Apple a) {
        return "green".equals(a.getColor());
    }

    public static boolean isHeavyApple(Apple a) {
        return a.getWeight() > 150;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
