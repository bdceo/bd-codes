package com.bdsoft.bdceo.dp.mediator.chat;

import lombok.Data;

/**
 * 聊天用户
 */
@Data
public abstract class User {

    private String name;
    private Mediator mediator;

    public User(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
        mediator.enterChat(this);
    }

    /**
     * 收发消息
     */
    abstract void send(String msg);

    abstract void receive(String msg);

}
