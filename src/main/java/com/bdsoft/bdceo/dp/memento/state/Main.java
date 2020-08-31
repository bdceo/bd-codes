package com.bdsoft.bdceo.dp.memento.state;

/**
 * 测试类
 */
public class Main {

    public static void main(String[] args) {
        Originator org = new Originator();
        org.setState("初始化");
        org.show();

        // 存储历史状态
        Caretaker taker = new Caretaker();
        taker.setMem(org.createMem());

        // 修改状态
        org.setState("运行中");
        org.show();

        // 恢复状态
        org.resetMem(taker.getMem());
        org.show();
    }

}
