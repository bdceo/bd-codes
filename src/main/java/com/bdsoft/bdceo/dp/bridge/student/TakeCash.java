package com.bdsoft.bdceo.dp.bridge.student;

/**
 * 抽象：人和行为
 */
public class TakeCash {

    /**
     * 不希望抽象部分和行为有一种固定的绑定关系，而是应该可以动态联系的
     */
    public static void main(String[] args) {
        // 初始化两家银行
        Bank icbc = new Icbc();
        Bank ccb = new Ccb();

        // 模拟两个学生取现
        Student s = new Master();
        s.takCash(icbc);

        s = new Graduate();
        s.takCash(ccb);
    }
}

/**
 * 学生取现行为抽象
 */
interface Student {
    void takCash(Bank mt);
}

// 本科生
class Graduate implements Student {

    public Graduate() {
    }

    public void takCash(Bank m) {
        System.out.println("本科生");
        m.atm();
        System.out.println();
    }
}

// 硕士生
class Master implements Student {

    public Master() {
    }

    public void takCash(Bank m) {
        System.out.println("硕士生");
        m.atm();
        System.out.println();
    }
}

/**
 * 银行
 */
interface Bank {
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
