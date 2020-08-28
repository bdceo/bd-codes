package com.bdsoft.bdceo.dp.cmd.player;

/**
 * 汇集播放器的命令，相当于对播放器的功能，进行了一个命令的拆解，和组装
 * 最终对外暴露的是此类的方法
 */
public class PlayerInvoker {

    private Command onCmd;
    private Command offCmd;
    private Command nextCmd;

    public PlayerInvoker(Command onCmd, Command offCmd, Command nextCmd) {
        this.onCmd = onCmd;
        this.offCmd = offCmd;
        this.nextCmd = nextCmd;
    }

    public void turnOn() {
        this.onCmd.exe();
    }

    public void turnOff() {
        this.offCmd.exe();
    }

    public void playNext() {
        this.nextCmd.exe();
    }

}
