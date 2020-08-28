package com.bdsoft.bdceo.dp.strategy.duck;

/**
 * 飞行方式
 */
public interface IFlyBehavior {
    String fly();
}

class FlyWithWings implements IFlyBehavior {

    @Override
    public String fly() {
        return "I will fly high ...";
    }
}

class FlyNoWay implements IFlyBehavior {

    @Override
    public String fly() {
        return "I can not fly ...";
    }
}
