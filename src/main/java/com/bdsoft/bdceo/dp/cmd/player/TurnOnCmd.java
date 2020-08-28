package com.bdsoft.bdceo.dp.cmd.player;

/**
 * 暴露播放器的：打开命令
 */
public class TurnOnCmd implements Command {

    private Player player;

    public TurnOnCmd(Player player) {
        this.player = player;
    }

    @Override
    public void exe() {
        player.turnOn();
    }
}
