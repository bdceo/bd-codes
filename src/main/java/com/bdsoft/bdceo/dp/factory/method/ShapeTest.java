package com.bdsoft.bdceo.dp.factory.method;

public class ShapeTest {

    public static void main(String[] args) {
        ShapeFactory sf1 = new CircleFactory();
        sf1.anOperation("Shape-Circle");

        System.out.println("-----------------");

        ShapeFactory sf2 = new SquareFactory();
        sf2.anOperation("Shape-Square");
    }

}

// 工厂方法定义，具体创建形状的过程留给子类实现
abstract class ShapeFactory {

    public void anOperation(String aName) {
        Shape s = this.factoryMethod(aName);
        System.out.println("The current shape is: " + s.name);
        s.draw();
        s.erase();
    }

    abstract Shape factoryMethod(String aName);
}

class CircleFactory extends ShapeFactory {
    Shape factoryMethod(String aName) {
        return new Circle(aName + " (created by CircleFactory)");
    }
}

class SquareFactory extends ShapeFactory {
    Shape factoryMethod(String aName) {
        return new Square(aName + " (created by SquareFactory)");
    }
}

// 形状的抽象定义，类似产品的概念
abstract class Shape {

    public String name;

    public Shape(String aName) {
        this.name = aName;
    }

    // 绘画
    public abstract void draw();

    // 擦除
    public abstract void erase();

}

class Circle extends Shape {

    public Circle(String name) {
        super(name);
    }

    public void draw() {
        System.out.println("It will draw a Circle");
    }

    public void erase() {
        System.out.println("It will erase a Circle");
    }

}

class Square extends Shape {

    public Square(String name) {
        super(name);
    }

    public void draw() {
        System.out.println("It will draw a Square");
    }

    public void erase() {
        System.out.println("It will erase a Square");
    }

}