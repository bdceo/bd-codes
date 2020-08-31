package com.bdsoft.bdceo.dp.mediator.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 聊天室
 */
public class ChatMediator implements Mediator {

    private List<User> users;

    public ChatMediator() {
        users = new ArrayList<>(10);
    }

    /**
     * 用户进入聊天室
     *
     * @param user 用户
     */
    @Override
    public void enterChat(User user) {
        Optional<User> check = users.stream().filter(u -> u.getName().equalsIgnoreCase(user.getName())).findAny();
        if (check.isPresent()) {
            return;
        } else {
            users.add(user);
        }
    }

    @Override
    public void chat(User user, String msg) {
        user.send(msg);
        users.stream().filter(u -> !u.getName().equalsIgnoreCase(user.getName())).forEach(u -> u.receive(msg));
    }
}
