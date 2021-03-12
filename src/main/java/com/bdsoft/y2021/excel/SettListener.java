package com.bdsoft.y2021.excel;


import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bdsoft.utils.StringUtil;
import com.bdsoft.y2021.huatai.DeliveryOrderPO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 结算-合同门店信息
 */
@Data
@Slf4j
public class SettListener extends AnalysisEventListener<SettPO> {

    // 解析存储的行数据
    private List<SettPO> data = new ArrayList();

    @Override
    public void invoke(SettPO order, AnalysisContext ctx) {
        if (StringUtil.isEmpty(order.getVin())) {
            log.info("车架号空，忽略");
            return;
        }
        log.info("解析行数据：{}", JSONUtil.toJsonStr(order));
        data.add(order);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext ctx) {
        log.info("数据解析完毕：{}", data.size());
    }


}
