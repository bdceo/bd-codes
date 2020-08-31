package com.bdsoft.bdceo.dp.memento.state;

import lombok.Data;

/**
 * 发起人
 */
@Data
public class Originator {

    /**
     * 对象状态，可以多个
     */
    private String state;

    /**
     * 创建备忘录
     */
    public Memento createMem() {
        return new Memento(state);
    }

    /**
     * 恢复状态
     */
    public void resetMem(Memento mem) {
        this.state = mem.getState();
    }

    public void show() {
        System.out.println("当前状态：" + state);
    }

}
