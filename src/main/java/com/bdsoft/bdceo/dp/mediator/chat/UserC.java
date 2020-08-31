package com.bdsoft.bdceo.dp.mediator.chat;

/**
 * 模拟用户C
 */
public class UserC extends User {

    public UserC(String name, Mediator mediator) {
        super(name, mediator);
    }

    @Override
    void send(String msg) {
        System.out.println("【" + getName() + "】发送消息>>" + msg);
    }

    @Override
    void receive(String msg) {
        System.out.println("\t【" + getName() + "】收到消息<<" + msg);
    }
}
