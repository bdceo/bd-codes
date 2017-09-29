package com.bdsoft.y2017.m08;

import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * Created by bdceo on 2017/8/9.
 */
public class CheckTime {

    public static void main(String[] args) {
        // 当前时区
        TimeZone curZone = TimeZone.getDefault();
        // 按北京时间算
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        LocalDateTime start = LocalDateTime.of(2017,8,9,16,0);
        LocalDateTime end = LocalDateTime.of(2017,9,1,0,0);
        LocalDateTime now = LocalDateTime.now();
        boolean valid = now.isAfter(start) && now.isBefore(end);
        System.out.println("有效期："+valid);
        // 恢复默认时区
        TimeZone.setDefault(curZone);
    }
}
