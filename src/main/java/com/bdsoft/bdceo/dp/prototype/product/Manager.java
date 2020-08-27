package com.bdsoft.bdceo.dp.prototype.product;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象管理类
 * <p>
 * Prototype模式中的角色
 * Prototype（原型）
 * Product角色负责定义用于复制现有实例来生成新实例的方法。在示例程序中，由Product接口扮演此角色。
 * <p>
 * ConcretePrototype（具体的原型）
 * ConcretePrototype角色负责实现复制现有实例并生成新实例的方法。在示例程序中，由MessageBox类和UnderlinePen类扮演此角色。
 * <p>
 * Client（使用者）
 * Client角色负责使用复制实例的方法生成新的实例。在示例程序中，由Manager类扮演此角色。
 */
public class Manager {

    private Map<String, Product> showCase = new HashMap<>();

    /**
     * 模拟客户端调用
     */
    public static void main(String[] args) {
        // 准备初始化原型，并本地缓存
        Manager manager = new Manager();
        UnderLinePen pen = new UnderLinePen("=");
        MessageBox start = new MessageBox("*");
        MessageBox line = new MessageBox("|");
        manager.register("pen", pen);
        manager.register("start", start);
        manager.register("line", line);

        // 生成
        Product p1 = manager.create("pen");
        p1.use("Java");

        Product p2 = manager.create("start");
        p2.use("Python");

        Product p3 = manager.create("line");
        p3.use("Finance");
    }

    /**
     * 初始化原型
     *
     * @param name  标识
     * @param proto 具体原型
     */
    public void register(String name, Product proto) {
        showCase.put(name, proto);
    }

    /**
     * 创建原型
     *
     * @param protoName
     * @return
     */
    public Product create(String protoName) {
        Product proto = showCase.get(protoName);
        if (proto == null) {
            throw new IllegalArgumentException("未知原型");
        }
        return proto.createClone();
    }

}
