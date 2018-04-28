package com.bdsoft.bdceo.java8;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Properties;

/**
 * Optional替代Null
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/3/29 15:23
 */
public class Chapter10 {

    public static void main(String[] args) {
        // 初始化Optional
        Optional<Car> car = Optional.empty();// 空的optional
        Optional<Car> car2 = Optional.of(new Car()); // 具体值
        // Optional<Car> car3 = Optional.of(null); // 直接nullpointerexception
        Optional<Car> car4 = Optional.ofNullable(null); // 允许null值的初始化

        // 提取对象值，采用map
        Optional<Insurance> ins = Optional.ofNullable(null);
        Optional<String> insName = ins.map(Insurance::getName);

        Optional<Person> optPerson = Optional.of(new Person());
//        Optional<String> insNames = optPerson.map(Person::getOcar).map(Car::getOinsurance).map(Insurance::getName); // 编译错误，Optional嵌套
        Optional<String> insNames = optPerson.map(Person::getCar).map(Car::getInsurance).map(Insurance::getName);
        // 采用flatMap
        String name = optPerson.flatMap(Person::getOcar).flatMap(Car::getOinsurance).map(Insurance::getName).orElse("Unknown");

        // 实战
        Map<String, Object> map = Maps.newHashMap();
        map.get("key");
        Optional<Object> value = Optional.ofNullable(map.get("key"));

        Properties prop = new Properties();
        prop.setProperty("a", "1");
        prop.setProperty("b", "int");
        prop.setProperty("c", "-1");

        System.out.println(readDuration(prop, "a"));
        System.out.println(readDuration(prop, "b"));
        System.out.println(readDuration(prop, "c"));
        System.out.println(readDuration(prop, "d"));

        optPerson.ifPresent(Person::getCar);
    }

    public static int readDuration(Properties prop, String name) {
        String value = prop.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    public static int readDurationOfOptional(Properties prop, String name) {
        return Optional.ofNullable(prop.getProperty(name)).flatMap(Chapter10::stringToInt).filter(i -> i > 0).orElse(0);
    }

    // 格式化，OptionalInt
    public static OptionalInt stringToInt2(String s) {
        try {
            return OptionalInt.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }

    // 格式化，Optional
    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * 采用flatMap提取嵌套Optional对象值
     *
     * @param person
     * @return
     */
    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getOcar).flatMap(Car::getOinsurance).map(Insurance::getName).orElse("Unknown");
    }

    public String getCarInsuranceName(Person person) {
        // 防御式检查
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        } else {
            return "Unknown";
        }

        // 过多的退出
        if (person == null) {
            return "Unknown";
        }
        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }
        return insurance.getName();

        // 直接读取
//        return person.getCar().getInsurance().getName();
    }
}


class Person {

    private Car car;
    private Optional<Car> ocar;

    public Optional<Car> getCarOfOptional() {
        return Optional.ofNullable(car);
    }

    public Optional<Car> getOcar() {
        return Optional.ofNullable(null);
    }

    public Car getCar() {
        return car;
    }
}

class Car {

    private Insurance insurance;
    private Optional<Insurance> oinsurance;

    public Optional<Insurance> getOinsurance() {
        return Optional.ofNullable(null);
    }

    public Insurance getInsurance() {
        return insurance;
    }
}

class Insurance {

    private String name;

    public String getName() {
        return name;
    }
}