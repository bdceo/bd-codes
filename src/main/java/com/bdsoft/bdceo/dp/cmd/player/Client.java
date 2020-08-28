package com.bdsoft.bdceo.dp.cmd.player;

/**
 * Created by Administrator on 2020/8/28.
 */
public class Client {

    public static void main(String[] args) {
        // 播放器
        Player player = new Player();

        // 命令
        Command onCmd = new TurnOnCmd(player);
        Command offCmd = new TurnOffCmd(player);
        Command nextCmd = new PlayNextCmd(player);

        // 命令适配器
        PlayerInvoker invoker = new PlayerInvoker(onCmd, offCmd, nextCmd);
        invoker.turnOn();
        invoker.playNext();
        invoker.turnOff();
    }

}
