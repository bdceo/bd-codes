package com.bdsoft.y2021.excel;


import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bdsoft.y2021.huatai.DeliveryOrderPO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 保险-回收门店信息
 */
@Data
@Slf4j
public class IcsListener extends AnalysisEventListener<IcsPO> {

    // 解析存储的行数据
    private List<IcsPO> data = new ArrayList();

    @Override
    public void invoke(IcsPO order, AnalysisContext ctx) {
        log.info("解析行数据：{}", JSONUtil.toJsonStr(order));
        data.add(order);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext ctx) {
        log.info("数据解析完毕：{}", data.size());
    }


}
