package com.bdsoft.bdceo.dp.prototype.student;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 学生
 */
@Slf4j
@Data
public class Student implements Cloneable {

    private String name;
    private int age;
    private ClassRoom room;

    public Student() {
        System.out.println("student 默认初始化");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("student 被复用");
        return super.clone();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", room=" + room.getName() +
                '}';
    }
}
