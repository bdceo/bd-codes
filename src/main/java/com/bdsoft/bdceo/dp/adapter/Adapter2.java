package com.bdsoft.bdceo.dp.adapter;

/**
 * 双向适配器
 */
public class Adapter2 {

    public static void main(String[] args) {
        DE de = new DE(new D(), new E());
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
    @Override
    public void funD1() {
        System.out.println("D.funD1");
    }

    @Override
    public void funD2() {
        System.out.println("D.funD2");
    }
}

class E implements IE {
    @Override
    public void funE1() {
        System.out.println("E.funE1");
    }

    @Override
    public void funE2() {
        System.out.println("E.funE2");
    }
}

// 组合D和E
class DE implements ID, IE {

    private ID id;
    private IE ie;

    public DE(ID id, IE ie) {
        this.id = id;
        this.ie = ie;
    }

    // 功能编写
    @Override
    public void funD1() {
        id.funD1();// 如果去掉就是功能替换
        ie.funE1();
    }

    // 重写其他功能，以免丢失
    @Override
    public void funD2() {
        id.funD2();
    }

    @Override
    public void funE2() {
        ie.funE2();
        id.funD2();
    }

    @Override
    public void funE1() {
        ie.funE1();
    }

}