package com.bdsoft.bdceo.dp.template;

/**
 * 模板方法模式
 */
public class TempTest {

    public static void main(String[] args) {
        BaseClass client = new MyClass();
        client.somMethod();
    }

}

abstract class BaseClass {

    // 定义算法骨架，执行序列
    void somMethod() {
        System.out.println("开始");
        step1();
        System.out.println("exe");
        step2();
        System.out.println("last");
        step3();
        System.out.println("结束");
    }

    // 算法步骤抽象
    protected abstract void step1();

    protected abstract void step2();

    protected abstract void step3();
}

// 模板实现
class MyClass extends BaseClass {
    @Override
    public void step1() {
        System.out.println("MyClass-step1");
    }

    @Override
    public void step2() {
        System.out.println("MyClass-step2");
    }

    @Override
    public void step3() {
        System.out.println("MyClass-step3");
    }
}

class MySecondClass extends BaseClass {
    @Override
    public void step1() {
        System.out.println("MySecondClass-step1");
    }

    @Override
    public void step2() {
        System.out.println("MySecondClass-step2");
    }

    @Override
    public void step3() {
        System.out.println("MySecondClass-step3");
    }
}