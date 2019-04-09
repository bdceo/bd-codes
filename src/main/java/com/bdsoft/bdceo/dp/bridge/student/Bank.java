package com.bdsoft.bdceo.dp.bridge.student;

/**
 * 银行
 */
public interface Bank {
    void atm();
}

class Ccb implements Bank {
    public void atm() {
        System.out.println("建设银行");
    }
}

class Icbc implements Bank {
    public void atm() {
        System.out.println("工商银行");
    }
}
