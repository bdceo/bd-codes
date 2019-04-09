package com.bdsoft.bdceo.dp.bridge.car;

public class MakeCar {

    /**
     * 制造汽车
     */
    public static void main(String[] se) {
        // 初始化两个发动机
        CarEngine e15 = new Engigeof1500CC();
        CarEngine e20 = new Engigeof2000CC();

        // 两台货车
        Car t15 = new Truck(e15);
        Car t20 = new Truck(e20);
        t15.setEngine();
        t20.setEngine();

        // 两台公交车
        System.out.println();
        Car b15 = new Bus(e15);
        Car b20 = new Bus(e20);
        b15.setEngine();
        b20.setEngine();
    }
}

abstract class Car {

    protected CarEngine engine;

    public abstract void setEngine();
}

class Truck extends Car {
    public Truck(CarEngine engine) {
        super.engine = engine;
    }

    public void setEngine() {
        System.out.print("Set Truck Engine: ");
        super.engine.setEngine();
    }
}

class Bus extends Car {
    public Bus(CarEngine engine) {
        super.engine = engine;
    }

    public void setEngine() {
        System.out.print("Set Bus Engine: ");
        super.engine.setEngine();
    }
}
