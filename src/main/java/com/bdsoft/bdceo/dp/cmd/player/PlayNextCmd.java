package com.bdsoft.bdceo.dp.cmd.player;

/**
 * 暴露播放器的：播放下一曲命令
 */
public class PlayNextCmd implements Command {

    private Player player;

    public PlayNextCmd(Player player){
        this.player= player;
    }

    @Override
    public void exe() {
        player.next();
    }
}
