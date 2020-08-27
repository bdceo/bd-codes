package com.bdsoft.bdceo.dp.adapter;

public class SimpleDemo {

    public static void main(String[] args) {
        WhoEat p = new Persons();
        p.eatBread();
        p.eatApple();

        System.out.println();
        p = new Monkey();
        p.eatBread();
        p.eatBanana();
    }
}

// 模拟吃东西的抽象
interface Eat {
    void eatBread();

    void eatApple();

    void eatBanana();
}

// 对吃的所有默认实现
class WhoEat implements Eat {
    @Override
    public void eatBread() {
        System.out.println("eat bread");
    }

    @Override
    public void eatApple() {
        System.out.println("eat apple");
    }

    @Override
    public void eatBanana() {
        System.out.println("eat banana");
    }
}

// 不同物种可能对吃有不同的实现
class Persons extends WhoEat {
    @Override
    public void eatBread() {
        System.out.println("person eat bread");
    }
}

class Monkey extends WhoEat {
    @Override
    public void eatBanana() {
        System.out.println("monkey eat banana");
    }
}