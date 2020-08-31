package com.bdsoft.bdceo.dp.memento.state;

import lombok.Data;

/**
 * 备忘录
 */
@Data
public class Memento {

    /**
     * 保存原始对象的内部状态
     */
    private String state;

    public Memento(String state) {
        this.state = state;
    }
}
