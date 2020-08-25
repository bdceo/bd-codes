package com.bdsoft.bdceo.dp.factory.simple;

import java.util.HashMap;
import java.util.Map;

public class SimpleFatory {

    // 模拟客户端调用和使用简单工厂
    public static void main(String[] args) {
        Car b = new Benz();
        b.run();

        // 默认工厂对象
        Car b2 = StaticFactory.getInstance();
        b2.run();

        // 缓存对象实例
        Car b3 = StaticFactory.getInstance2("ford");
        if (b3 != null) {
            b3.run();
        } else {
            System.out.println("no this car!");
        }

        // 通过反射，动态创建对象实例
        Car b4 = StaticFactory.getInstance3("Nession");
        if (b4 != null) {
            b4.run();
        } else {
            System.out.println("no this car!");
        }
    }
}

// 工厂类，专门负责不同类型汽车的实例化
class StaticFactory {

    private static Map<String, Car> cars = new HashMap<>();
    private static final String BASE = "com.bdsoft.bdceo.dp.factory.";

    static {
        cars.put("benz", new Benz());
        cars.put("ford", new Ford());
        cars.put("nession", new Nession());
    }

    // 默认福特汽车
    public static Car getInstance() {
        return new Ford();
    }

    // 根据类型获取汽车实例
    public static Car getInstance2(String type) {
        return cars.get(type);
    }

    // 通过反射获取具体的实例
    public static Car getInstance3(String type) {
        try {
            Car c = (Car) Class.forName(BASE + type).newInstance();
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

// 产品类抽象定义
interface Car {
    void run();

    void stop();
}

// 具体产品
class Benz implements Car {

    public void run() {
        System.out.println("benz run");
    }

    public void stop() {
        System.out.println("benz stop");
    }
}

class Ford implements Car {

    public void run() {
        System.out.println("ford run");
    }

    public void stop() {
        System.out.println("ford stop");
    }
}

class Nession implements Car {

    public void run() {
        System.out.println("nession run");
    }

    public void stop() {
        System.out.println("nession stop");
    }
}
