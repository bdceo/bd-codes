package com.bdsoft.y2021.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 合同：车辆门店
 */
@Data
public class SettPO {

    @ExcelProperty(value = "车架号", index = 0)
    private String vin;
    @ExcelProperty(value = "门店code", index = 1)
    private String storeCode;
    @ExcelProperty(value = "门店名", index = 2)
    private String storeName;

}
