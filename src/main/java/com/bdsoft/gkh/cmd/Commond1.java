package com.bdsoft.gkh.cmd;

import java.util.HashMap;

public class Commond1 {

    /**
     * 命令模式:客户端调用命令时无需关心来源
     */
    public static void main(String[] args) {
        String cmd = "001";

        // 原始方式，客户端关注太多
        // if (cmd.equals("001")) {
        // Module1 m1 = new Module1();
        // m1.executeCommand();
        // } else if (cmd.equals("002")) {
        // Module2 m2 = new Module2();
        // m2.executeCommand();
        // } else {
        // System.out.println("no cmd execute!");
        // }

        // 命令模式：客户端只需调用命令调配器，获取命令执行对象，执行任务
        CommandConstructor.constructCommand();
        Command exe = CommandConstructor.getCmd(cmd);
        exe.executeCommand();
    }
}

// 命令集合器
class CommandConstructor {

    // 将每个模块和命令映射起来
    private static HashMap<String, Command> cmdPool = new HashMap<String, Command>();

    /**
     * 初始化命令集合
     */
    static void constructCommand() {
        Module1 m1 = new Module1();
        Module2 m2 = new Module2();
        cmdPool.put("001", m1);
        cmdPool.put("002", m2);
    }

    static Command getCmd(String cmd) {
        return cmdPool.get(cmd);
    }
}

// 命令抽象
interface Command {
    void executeCommand();
}

/**
 * 命令1
 */
class Module1 implements Command {
    public void fun1() {
        System.out.println("Module1.fun1");
    }

    public void executeCommand() {
        System.out.println("Module1.executeCommand");
    }
}

/**
 * 命令2
 */
class Module2 implements Command {
    public void fun2() {
        System.out.println("Module2.fun2");
    }

    public void executeCommand() {
        System.out.println("Module2.executeCommand");
    }
}