package com.bdsoft.bdceo.dp.facade;

public class CarFacade {

    /**
     * Façade模式的几个要点：<br/>
     * 1、从客户程序的角度看，Facade模式不仅简化了整个组件系统的接口， 同时对于组件内部与外部客户程序来说，
     * 从某种程度上也达到了一种“解耦”的效果——内部子系统的任何变化不会影响到Facade接口的变化。<br/>
     * 2、Facade设计模式更注重从架构的层次去看整个系统，而不是单个类的层次。 Facade很多时候更是一种架构设计模式。
     */
    public static void main(String[] args) {
        CarFacade cf = new CarFacade();
        cf.run();
        cf.stop();
    }

    /**
     * 隐藏汽车构造细节
     */
    private Car car = new Car();

    /**
     * 对外暴露简单方法
     */
    public void run() {
        System.out.println(car.engine.engineWork());
        for (int i = 0; i < car.wheels.length; i++) {
            System.out.println(car.wheels[i].wheelCircumratate());
        }
    }

    public void stop() {
        System.out.println(car.engine.engineStop());
        for (int i = 0; i < car.wheels.length; i++) {
            System.out.println(car.wheels[i].wheelStop());
        }
    }
}

class Engine {
    public String engineWork() {
        return "BMW's Engine is Working";
    }

    public String engineStop() {
        return "BMW's Engine is stoped";
    }
}

class Wheel {
    public String wheelCircumratate() {
        return "BMW's Wheels are circumrotaing";
    }

    public String wheelStop() {
        return "BMW's Wheels are stoped";
    }
}

class Car {

    public Wheel[] wheels = new Wheel[4];
    public Engine engine = new Engine();

    public Car() {
        for (int i = 0; i < wheels.length; i++) {
            wheels[i] = new Wheel();
        }
    }
}
