package com.bdsoft.bdceo.dp.bridge.car;

/**
 * 汽车引擎
 */
public interface CarEngine {
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
