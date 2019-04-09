package com.bdsoft.bdceo.dp.adapter;

public class SimpleAdapter {

    // 单相适配：继承
    public static void main(String[] args) {
        // 被适配对象
        Tmp t = new B();
        // 包装的外层类
        NewA a = new NewA(t);
        a.funA();
    }

}

// 单向适配，调用A方法时同时调用B方法，采用继承法，扩展A来模拟实现
class A {
    public void funA() {
        System.out.println("A.funA");
    }
}

interface Tmp {
    void fun();
}

// B面向接口编程
// class B {
class B implements Tmp {
    // public void funB() {
    // System.out.println("B.funB");
    public void fun() {
        System.out.println("Tmp.B.fun");
    }
}

class NewA extends A {
    // 绑定其他的实现
    private Tmp tmp;

    public NewA(Tmp t) {
        this.tmp = t;
    }

    // 扩展A实现
    public void funA() {
        super.funA();// 可以调用父类逻辑也可以不调用实现完全重写
        // new B().funB();
        tmp.fun();
    }
}
