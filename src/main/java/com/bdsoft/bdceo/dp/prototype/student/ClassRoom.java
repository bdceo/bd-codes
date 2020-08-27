package com.bdsoft.bdceo.dp.prototype.student;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 教室
 */
@Slf4j
@Data
public class ClassRoom implements Cloneable {

    private String name;

    public ClassRoom() {
        System.out.println("classroom 默认初始化");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("classroom 被复用");
        return super.clone();
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "name='" + name + '\'' +
                '}';
    }
}
