package com.bdsoft.bdceo.dp.strategy.duck;

/**
 * 声音模式
 */
public interface IQuackBehavior {
    String quack();
}

class QuackOne implements IQuackBehavior {

    @Override
    public String quack() {
        return "GAGAGA";
    }

}

class QuackThree implements IQuackBehavior {

    @Override
    public String quack() {
        return "gagaga";
    }

}

class QuackTwo implements IQuackBehavior {

    @Override
    public String quack() {
        return "ga...ga...ga";
    }

}