package com.bdsoft.bdceo.dp.bridge.car;

/**
 * 抽象：汽车和发动机
 */
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
        t15.setEngine();
        Car t20 = new Truck(e20);
        t20.setEngine();

        // 两台公交车
        System.out.println();
        Car b15 = new Bus(e15);
        b15.setEngine();
        Car b20 = new Bus(e20);
        b20.setEngine();
    }
}

abstract class Car {

    protected CarEngine engine;

    abstract void setEngine();
}

class Truck extends Car {
    public Truck(CarEngine engine) {
        super.engine = engine;
    }

    @Override
    public void setEngine() {
        System.out.print("Set Truck Engine: ");
        super.engine.setEngine();
    }
}

class Bus extends Car {
    public Bus(CarEngine engine) {
        super.engine = engine;
    }

    @Override
    public void setEngine() {
        System.out.print("Set Bus Engine: ");
        super.engine.setEngine();
    }
}

/**
 * 汽车引擎
 */
interface CarEngine {
    void setEngine();
}

class Engigeof1500CC implements CarEngine {
    public void setEngine() {
        System.out.println("1500cc");
    }
}

class Engigeof2000CC implements CarEngine {
    public void setEngine() {
        System.out.println("2000cc");
    }
}
