package com.bdsoft.bdceo.dp.observer;

import java.util.Observable;
import java.util.Observer;

public class Observer1 {

    /**
     * 观察者模式，使用jdk提供的接口开发
     * <p>
     * 观察者实现Observer接口，被观察者继承Observable类
     */
    public static void main(String[] args) {
        // 观察者
        Customer2 cus = new Customer2("bdceo");
        // 被观察者
        Product product = new Product("apple", 12);

        // 观察者和被观察者绑定，可以绑定多个关注着
        // product.addObserver(cus);
        product.setObserver(cus);

        // 被观察者发布事件
        product.changePrice(10);
    }

}

// 产品类，产品的价格变动要通知客户
// 目标：产品不关心通知哪些客户，客户不关心哪个产品发出的通知
// 产品：被观察者
class Product extends Observable {

    private String pname;
    private double price;

    public Product(String name, double price) {
        this.pname = name;
        this.price = price;
    }

    public void setObserver(Observer obs) {
        this.addObserver(obs);
    }

    public void changePrice(double newPrice) {
        this.price = newPrice;
        System.out.println("the " + pname + " price change to " + this.price);
        // 通知观察者
        this.setChanged();// 设置变化点，通知观察者
        this.notifyObservers(newPrice);
    }
}

/**
 * 客户：观察者
 */
class Customer2 implements Observer {

    private String cname;

    public Customer2(String name) {
        this.cname = name;
    }

    /**
     * 通知回调
     *
     * @param src 通知来源
     * @param arg 通知参数
     */
    @Override
    public void update(Observable src, Object arg) {
        System.out.println(cname + " know the price changed!");
        System.out.println("the new price = " + arg);
        System.out.println("change from " + src.toString());
    }

}
