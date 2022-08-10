package com.example.excel.entity;

import com.example.excel.util.ExcelImport;
import lombok.Data;

/**
 * C02
 */
@Data
public class CustomerInformation {

    /**
     * 获取行号
     */
    private double rowNum;

    /**
     * 主键
     */
    private int id;

    /**
     * 客户简称
     */
    @ExcelImport(value = "客户简称",required = true,dataCompliance = true)
    private String customerAbbreviation;
    /**
     * 成本往来模板
     */
    @ExcelImport(value = "成本往来模板",required = true)
    private String costTransactionTemplate;
    /**
     * 错误提示
     */
    private String rowTips;
    /**
     * 原始数据
     */
    private String  rowData;
}
