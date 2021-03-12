package com.bdsoft.y2021.excel;

import com.alibaba.excel.EasyExcel;
import com.bdsoft.utils.CollectionUtil;
import com.bdsoft.utils.FileUtil;
import com.bdsoft.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * EasyExcel 解析 交割单
 * <p>
 * https://alibaba-easyexcel.github.io/index.html
 */
@Slf4j
public class StoreParse {

    /**
     * 测试华泰交割单解析
     */
    public static void main(String[] args) {
        IcsListener ics = new IcsListener();
        String icsFile = "e:\\download\\保险门店.xlsx";
        EasyExcel.read(icsFile, IcsPO.class, ics).sheet().doRead();

        SettListener sett = new SettListener();
        String settFile = "e:\\download\\车架号对应门店.xlsx";
        EasyExcel.read(settFile, SettPO.class, sett).sheet().doRead();

        // 数据比对
        List<IcsPO> icsList = ics.getData();
        List<SettPO> settList = sett.getData();
        log.info("开始比对：ics={}, sett={}", icsList.size(), settList.size());
        Map<String, List<SettPO>> vinMap = settList.stream().collect(Collectors.groupingBy(SettPO::getVin));

        // 输出的sql
        List<String> sqlList = new ArrayList<>(icsList.size() / 2);
        String tmp = "update t_receipt r, t_mst_organization o set r.sale_store_id=o.id where r.id={0} and o.code=''{1}'';";

        for (IcsPO ipo : icsList) {
            List<SettPO> spoList = vinMap.get(ipo.getVin());
            if (!CollectionUtils.isEmpty(spoList) && spoList.size() == 1) {
                SettPO spo = spoList.get(0);
                String sql = MessageFormat.format(tmp, ipo.getId(), spo.getStoreCode());
                if (ipo.getStoreId() == null) {
                    sqlList.add(sql);
                    log.info("销售门店缺失：{}", ipo.getId());
                } else if (StringUtil.isNotEmpty(ipo.getStoreCode()) && !ipo.getStoreCode().equals(spo.getStoreCode())) {
                    sqlList.add(sql);
                    log.info("销售门店不匹配：{}", ipo.getId());
                }
            } else {
                log.info("车架号缺失或存在多个门店：{}", ipo.getVin());
            }
        }

        // 输出
        String sqlFile = "e:\\download\\dml.sql";
        String content = StringUtils.join(sqlList, "\r\n");
        FileUtil.writeFile(sqlFile, content, true);
    }

}
