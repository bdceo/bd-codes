package com.bdsoft.y2017.m07;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * 时间，时区
 */
public class TestDateLocal {

    public static void main(String[] args) {
        String utc = "2017-07-30 10:16:15";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        TemporalAccessor ta = dtf.parse(utc);
        System.out.println("ta=" + ta.toString());

        LocalDateTime ldt = LocalDateTime.parse(utc, dtf);
        System.out.println("ldt=" + ldt.toString());

//        ZonedDateTime zdt = ZonedDateTime.parse(utc, dtf);
//        System.out.println("zdt=" + zdt.toString());

        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.toString());

        clock = Clock.systemUTC();
        System.out.println(clock.toString());
    }

}
