package com.bdsoft.y2021.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 保险：车架号门店
 */
@Data
public class IcsPO {

    @ExcelProperty(value = "vin", index = 0)
    private String vin;
    @ExcelProperty(value = "sale_store_id", index = 1)
    private Long storeId;
    @ExcelProperty(value = "code", index = 2)
    private String storeCode;
    @ExcelProperty(value = "name", index = 3)
    private String storeName;

}
