package com.bdsoft.bdceo.dp.mediator.chat;

/**
 * 模拟用户B
 */
public class UserB extends User {

    public UserB(String name, Mediator mediator) {
        super(name, mediator);
    }

    @Override
    void send(String msg) {
        System.out.println("【"+getName()+"】发送消息>>" + msg);
    }

    @Override
    void receive(String msg) {
        System.out.println("\t【"+getName()+"】收到消息<<" + msg);
    }
}
