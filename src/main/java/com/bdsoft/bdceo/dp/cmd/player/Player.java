package com.bdsoft.bdceo.dp.cmd.player;

import lombok.extern.slf4j.Slf4j;

/**
 * 播放器，具有执行命令功能的具体功能类
 */
@Slf4j
public class Player {

    public void turnOff() {
        log.info("关闭");
    }

    public void turnOn() {
        log.info("打开");
    }

    public void next() {
        log.info("下一曲");
    }
}
