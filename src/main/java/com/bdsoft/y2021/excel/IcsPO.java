package com.bdsoft.y2021.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 保险：车架号门店
 */
@Data
public class IcsPO {

    @ExcelProperty(value = "id", index = 0)
    private Long id;
    @ExcelProperty(value = "vin", index = 1)
    private String vin;
    @ExcelProperty(value = "sale_store_id", index = 2)
    private Long storeId;
    @ExcelProperty(value = "code", index = 3)
    private String storeCode;
    @ExcelProperty(value = "name", index = 4)
    private String storeName;

}
