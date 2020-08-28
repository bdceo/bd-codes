package com.bdsoft.bdceo.dp.cmd.player;

/**
 * 暴露播放器的：关闭命令
 */
public class TurnOffCmd implements Command {

    private Player player;

    public TurnOffCmd(Player player){
        this.player= player;
    }

    @Override
    public void exe() {
        player.turnOff();
    }
}
