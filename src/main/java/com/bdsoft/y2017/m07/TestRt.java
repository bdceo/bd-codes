package com.bdsoft.y2017.m07;

import com.bdsoft.bdceo.ibatis.Main;

/**
 * Created by bdceo on 2017/7/20.
 */
public class TestRt {

    public static void main(String[] args) {

        if(true){
            trt();
            return;
        }
        System.out.print(1);

    }

    static void trt() {
        throw new RuntimeException("抛了");
    }

}
