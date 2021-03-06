package com.bdsoft.y2021.huatai;

import com.alibaba.excel.EasyExcel;

/**
 * EasyExcel 解析 交割单
 * <p>
 * https://alibaba-easyexcel.github.io/index.html
 */
public class DeliveryParser {

    /**
     * 测试华泰交割单解析
     */
    public static void main(String[] args) {

        String fileName = "D:\\download\\422126924\\投资理财\\华泰\\华泰交割单.xlsx";

        EasyExcel.read(fileName, DeliveryOrderPO.class, new DeliverOrderListener())
                .registerConverter(new DateConverter())
                .sheet().doRead();

    }

}
