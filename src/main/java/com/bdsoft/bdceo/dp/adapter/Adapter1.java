package com.bdsoft.bdceo.dp.adapter;

public class Adapter1 {

    /**
     * 单向适配器
     */
    public static void main(String[] args) {
        // 被适配对象
        IEM b = new B1();
        IEM c = new C1();

        // 原始用法
        new A1().funA();
        System.out.println("------------");

        // 适配包装对象，将B或C适配到A上，但是对外使用方式不变
        A1 sa = new SubA(c);
        // 原有功能+新功能的集成
        sa.funA();
    }

}

// 定义一个接口，管理所有的扩展模块(被动模块)
interface IEM {
    void extendFun();
}

class B1 implements IEM {
    public void extendFun() {
        System.out.println("去噪声-旧算法");
    }
}

class C1 implements IEM {
    public void extendFun() {
        System.out.println("去噪声-新算法");
    }
}

// 如果将B接到A上用，这叫做单向适配器模式，A主动，B被动
// 单向适配器模式要点：继承法
// 考虑被动方以后可能被切换，为了方便，最好面向接口编程
class A1 {
    public void funA() {
        System.out.println("图像清晰化");
    }
}

// 将A进行扩展，以后将SubA当成A来用
class SubA extends A1 {

    // 完全面向接口编程，可以将iem任何实现类对象接入(可以用Spring注入)
    private IEM iem;

    public SubA(IEM iem) {
        this.iem = iem;
    }

    @Override
    public void funA() {
        super.funA();// 值得一提的是：这句代码可以去掉，如果去掉，就是功能替换
        iem.extendFun();
    }
}