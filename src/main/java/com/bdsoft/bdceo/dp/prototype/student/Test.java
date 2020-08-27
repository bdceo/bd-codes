package com.bdsoft.bdceo.dp.prototype.student;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试类
 */
@Slf4j
public class Test {

    public static void main(String[] args) throws Exception {
        // 准备原型对象
        ClassRoom proRoom = new ClassRoom();
        proRoom.setName("清华姚班");

        Student proStu = new Student();
        proStu.setRoom(proRoom);
        proStu.setName("学霸");
        proStu.setAge(16);

        System.out.println(proStu);

        // 复制原型对象
        Student ding = (Student) proStu.clone();
        System.out.println("ding=>" + ding);
        ding.setName("丁老师");
        ding.setAge(32);
        System.out.println("ding=>" + ding);

        Student tmp = (Student)proStu.clone();
        tmp.setName("老丁");
        tmp.getRoom().setName("河北大学");
        System.out.println("tmp=>" + tmp);
        log.info("{}",tmp);

    }
}
