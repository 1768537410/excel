package com.example.excel.entity;

import com.example.excel.util.ExcelExport;
import com.example.excel.util.ExcelImport;
import lombok.Data;

/**
 * @author zjc
 * C04
 */
@Data
public class SubCustomerInformation {

    /**
     * 获取行号
     */
    private double rowNum;


    /**
     * 主键
     */
    private int id;

    /**
     * 子客户编号
     */
    @ExcelImport(value = "子客户编号",required = true)
    @ExcelExport(value = "子客户编号")
    private String subcustomerNumber;
    /**
     * 子客户全称
     */
    @ExcelImport(value = "子客户全称",required = true)
    @ExcelExport(value = "子客户全称")
    private String subcustomerName;
    /**
     * 子客户简称
     */
    @ExcelImport(value = "子客户简称",required = true)
    @ExcelExport(value = "子客户简称")
    private String subcustomerAbbreviation;

    /**
     * 客户简称
     */
    @ExcelImport(value = "客户简称",required = true,dataCompliance = true)
    @ExcelExport(value = "客户简称")
    private String customerAbbreviation;

    /**
     * 客户编号
     */
    @ExcelImport(value = "客户编号",required = true,customerNumber = true)
    @ExcelExport(value = "客户编号")
    private String customerNumber;
    /**
     * 备注
     */
    @ExcelImport("备注")
    @ExcelExport(value = "备注")
    private String remarks;

    /**
     * 错误提示
     */
    @ExcelExport(value = "错误提示")
    private String rowTips;
    /**
     * 原始数据
     */
    private String  rowData;

}
