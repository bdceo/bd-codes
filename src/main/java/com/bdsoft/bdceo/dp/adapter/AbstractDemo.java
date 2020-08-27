package com.bdsoft.bdceo.dp.adapter;

import lombok.Data;

public class AbstractDemo {

    /**
     * 抽象类
     */
    public static void main(String[] args) {
        new Worker("Whilton", 32, 6300f).say();
        new Student("Tom", 16, 120f).say();
    }
}

// 人的抽象
@Data
abstract class Person {

    private String name;
    private int age;

    public Person(String n, int a) {
        this.setName(n);
        this.setAge(a);
    }

    // 通用功能：说话
    public void say() {
        System.out.println(this.getInfo());
    }

    // 说话内容子类实现
    abstract String getInfo();

}

// 工人
@Data
class Worker extends Person {

    private double money;

    public Worker(String name, int age, float money) {
        super(name, age);
        this.setMoney(money);
    }

    @Override
    public String getInfo() {
        return "My name is " + super.getName() + ",I am " + super.getAge()
                + " years old and my salary is " + this.getMoney();
    }
}

// 学生
@Data
class Student extends Person {

    private float score;

    public Student(String name, int age, float score) {
        super(name, age);
        this.setScore(score);
    }

    @Override
    public String getInfo() {
        return "My name is " + super.getName() + ",I am " + super.getAge()
                + " years old, and my score is " + this.getScore();
    }
}
