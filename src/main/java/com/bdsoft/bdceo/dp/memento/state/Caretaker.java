package com.bdsoft.bdceo.dp.memento.state;

import lombok.Data;

/**
 * 管理者
 */
@Data
public class Caretaker {

    /**
     * 持有备忘录
     */
    private Memento mem;

}
