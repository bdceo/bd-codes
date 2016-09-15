package com.bdsoft.bdceo.dp.adapter;

public class AbstractDemo {

	public static void main(String[] args) {
		Person w = new Worker("Whilton", 32, 6300f);
		Person s = new Student("Tom", 16, 120f);
		
		w.say();
		s.say();
	}
}

// 人的抽象
abstract class Person {
	private String name;
	private int age;

	public Person(String n, int a) {
		this.setName(n);
		this.setAge(a);
	}

	public abstract String getSay();

	public void say() {
		System.out.println(this.getSay());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

// 工人
class Worker extends Person {
	private double money;

	public Worker(String name, int age, float money) {
		super(name, age);
		this.setMoney(money);
	}

	public String getSay() {
		return "My name is " + super.getName() + ",I am " + super.getAge()
				+ " years old and my salary is " + this.getMoney();
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
}

// 学生
class Student extends Person {
	private float score;

	public Student(String name, int age, float score) {
		super(name, age);
		this.setScore(score);
	}

	public String getSay() {
		return "My name is " + super.getName() + ",I am " + super.getAge()
				+ " years old, and my score is " + this.getScore();
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
}
