package com.bdsoft.bdceo.dp.dutylist;

import com.google.common.collect.Lists;

public class Level1Duty extends AbsDuty {

    private IDuty nextDuty = new Level2Duty();

    public Level1Duty() {
        this("周二");
    }

    public Level1Duty(String name) {
        this.name = name;
        this.myDuty = Lists.newArrayList(1, 2);
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
