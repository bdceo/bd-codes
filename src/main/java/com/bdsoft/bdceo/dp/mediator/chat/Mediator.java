package com.bdsoft.bdceo.dp.mediator.chat;

/**
 * 聊天室
 */
public interface Mediator {

    /**
     * 进入聊天室
     *
     * @param user 用户
     */
    void enterChat(User user);

    /**
     * 聊天消息中转
     *
     * @param user 用户
     * @param msg  消息
     */
    void chat(User user, String msg);
}
