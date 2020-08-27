package com.bdsoft.bdceo.dp.adapter;

/**
 * 双向适配器，采用组合方法，在一个新的组合类里同时拥有不同实现的引用
 */
public class EachAdapter {

    public static void main(String[] args) {
        // Frame1 f1 = new Frame1();
        // Frame2 f2 = new Frame2();

        // 面向接口
        F1 f1 = new Frame1();
        F2 f2 = new Frame2();

        Frame f = new Frame(f1, f2);
        f.newChn();
        f.newRed();
    }

}

// 面向接口编程
interface F1 {
    void red();
}

interface F2 {
    void chn();
}

class Frame1 implements F1 {
    public void red() {
        System.out.println("Frame1.red");
    }
}

class Frame2 implements F2 {
    public void chn() {
        System.out.println("Frame2.chn");
    }
}

/**
 * 面向接口，通过接口去适配
 */
class Frame implements F1, F2 {
    // private Frame1 f1;
    private F1 f1;
    // private Frame2 f2;
    private F2 f2;

    // public Fram(Frame1 f1, Frame2 f2) {
    public Frame(F1 f1, F2 f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    /**
     * 新的对外暴露的方法
     */
    public void newChn() {
        // f1.red();
        f2.chn();
    }

    public void newRed() {
        // f2.chn();
        f1.red();
    }

    @Override
    public void red() {
        f1.red();
    }

    @Override
    public void chn() {
        f2.chn();
    }
}