package com.bdsoft.bdceo.dp.mediator.chat;

/**
 * 测试入口
 */
public class Main {

    public static void main(String[] args) {
        Mediator mediator = new ChatMediator();
        User a = new UserA("丁", mediator);
        User b = new UserB("王", mediator);
        User c = new UserC("周", mediator);

        mediator.chat(a, "中午吃什么");
        mediator.chat(b, "看下排名吧");
        mediator.chat(c, "好的，吃炒菜");
    }}
