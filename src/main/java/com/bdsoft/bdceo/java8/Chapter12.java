package com.bdsoft.bdceo.java8;

import com.mysql.jdbc.util.TimezoneDump;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.zone.ZoneRules;
import java.util.TimeZone;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * 日期和时间API
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/3/28 17:53
 */
public class Chapter12 {

    public static void main(String[] args) {
        // 不可变日期
        LocalDate date = LocalDate.of(2018, 03, 28);
        System.out.println(date.toString());
        date = LocalDate.now();

        int year = date.getYear();
        year = date.get(ChronoField.YEAR);

        date = LocalDate.parse("2018-03-28");
        date = LocalDate.parse("2018-03-28", DateTimeFormatter.ISO_DATE);
        date = LocalDate.parse("2018 03 28", DateTimeFormatter.ofPattern("yyyy MM dd"));
        System.out.println(date);
        date.format(DateTimeFormatter.BASIC_ISO_DATE); // 20180328
        date.format(DateTimeFormatter.ISO_DATE);//2018-03-28

        // 时间
        LocalTime time = LocalTime.of(17, 57, 42);
        System.out.println(time.toString());

        time = LocalTime.parse("17:57:42");
        time = LocalTime.parse("17 57 42", DateTimeFormatter.ofPattern("HH mm ss"));
        System.out.println(time);

        // 合并日期和时间
        LocalDateTime dt = LocalDateTime.of(2018, 03, 28, 17, 57, 42);
        dt = LocalDateTime.of(date, time);
        dt = date.atTime(17, 57, 42);
        dt = date.atTime(time);
        dt = time.atDate(date);
        date = dt.toLocalDate();
        time = dt.toLocalTime();
        System.out.println(dt.toString());

        // 瞬时状态：距1970-01-01依赖的秒值
        Instant ins = Instant.ofEpochSecond(12);
        ins = Instant.ofEpochMilli(12 * 1000);
        System.out.println(ins);

        ZoneOffset zo = ZoneOffset.of("-08:00");
        ins = dt.toInstant(zo);
        System.out.println(ins);

        // 时间间隔：date之间，time之间，datetime之间
        LocalTime time2 = LocalTime.of(14, 1, 45);
        Duration dur = Duration.between(time, time2);
        System.out.println(dur);
        dur = Duration.ofDays(2); // 2天时差
        dur = Duration.ofMinutes(33);// 33分钟时差

        // 时间段
        Period per = Period.between(LocalDate.of(2009,4,2), LocalDate.of(2018,4,2));
        System.out.println(per);
        per = Period.ofDays(4);

        // 修改时间
        LocalDate date2= date.withYear(1988);
        System.out.println(date2);
        LocalDate date3 = date2.withMonth(5);
        System.out.println(date3);
        LocalDate date4 = date3.minusDays(11);
        System.out.println(date4);

        // 复杂时间修改：下一个星期五
        LocalDate date5 = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date5);
        LocalDate date6 = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(date6);

        // 时区
        ZoneId shanghai = TimeZone.getDefault().toZoneId();
        ZoneId rome = ZoneId.of("Europe/Rome");
        System.out.println(shanghai);
        ZonedDateTime dt1 = date.atStartOfDay(rome);
        System.out.println(dt1);
        ZonedDateTime dt2 = dt.atZone(rome);
        System.out.println(dt2);

        ZoneOffset zo1 = ZoneOffset.of("-07:00");
        OffsetDateTime odt = OffsetDateTime.of(LocalDateTime.of(2018,3,29,15,20), zo1);
        System.out.println(odt);

    }


}
