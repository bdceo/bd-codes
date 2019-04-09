package com.bdsoft.bdceo.dp.dutylist;

import com.google.common.collect.Lists;

public class Level2Duty extends AbsDuty {

    private IDuty nextDuty = new Level3Duty();

    public Level2Duty() {
        this("周四");
    }

    public Level2Duty(String name) {
        this.name = name;
        this.myDuty = Lists.newArrayList(3, 4);
    }

    @Override
    public String next(Integer level) {
        if (myDuty.contains(level)) {
            return info();
        } else {
            return nextDuty.next(level);
        }
    }

}
