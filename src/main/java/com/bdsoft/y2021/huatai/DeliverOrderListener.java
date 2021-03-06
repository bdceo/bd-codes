package com.bdsoft.y2021.huatai;


import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 华泰交割单Excel解析
 */
@Slf4j
public class DeliverOrderListener extends AnalysisEventListener<DeliveryOrderPO> {


    // 解析存储的行数据
    private List<DeliveryOrderPO> orderList = new ArrayList();

    @Override
    public void invoke(DeliveryOrderPO order, AnalysisContext ctx) {
        log.info("解析行数据：{}", JSONUtil.toJsonStr(order));
        orderList.add(order);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext ctx) {
        log.info("数据解析完毕：准备初始化：{}", orderList.size());
    }


}
