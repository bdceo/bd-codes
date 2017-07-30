package com.bdsoft.y2017.m07;

import java.time.ZonedDateTime;

/**
 * Created by bdceo on 2017/7/30.
 */
public class TestDateLocal {

    public static void main(String[] args) {
        String utc = "2017-07-30 10:16:15";

        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt.toString());
    }
}
