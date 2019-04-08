package com.bdsoft.gkh.adapter;

public class Adapter2 {

    /**
     * 双向适配器
     */
    public static void main(String[] args) {
        DE de = new DE();
        de.setIE(new E());
        de.setID(new D());

        de.funD1();
        de.funE2();
    }
}

//双向适配器模式：组合法
interface ID {
    void funD1();

    void funD2();
}

interface IE {
    void funE1();

    void funE2();
}

class D implements ID {
    public void funD1() {
        System.out.println("D.funD1");
    }

    public void funD2() {
        System.out.println("D.funD2");
    }
}

class E implements IE {
    public void funE1() {
        System.out.println("E.funE1");
    }

    public void funE2() {
        System.out.println("E.funE2");
    }
}

class DE implements ID, IE {// 组合D和E
    private ID id;
    private IE ie;

    public void setID(ID id) {// 可以通过Spring框架来装配
        this.id = id;
    }

    public void setIE(IE ie) {
        this.ie = ie;
    }

    // 功能编写
    public void funD1() {
        id.funD1();// 如果去掉就是功能替换
        ie.funE1();
    }

    public void funE2() {
        ie.funE2();
        id.funD2();
    }

    // 重写其他功能，以免丢失
    public void funD2() {
        id.funD2();
    }

    public void funE1() {
        ie.funE1();
    }
}