package com.example.excel.entity;

import com.example.excel.util.ExcelExport;
import com.example.excel.util.ExcelImport;
import lombok.Data;

/**
 * C06
 * @author user
 */
@Data
public class ServiceAgreementItems {

    /**
     * 获取行号
     */
    private double rowNum;
    /**
     * 主键
     */
    private int id;
    /**
     * 服务协议名称
     */
    @ExcelImport(value = "服务协议名称",required = true,ListServiceAgreementNameBuilder = true)
    @ExcelExport(value = "服务协议名称")
    private String serviceAgreementName;
    /**
     * 服务产品
     */
    @ExcelImport(value = "服务产品",serviceProducts = true)
    @ExcelExport(value = "服务产品")
    private String serviceProducts;
    /**
     * 服务项目
     */
    @ExcelImport(value = "服务项目",serviceItems = true)
    @ExcelExport(value = "服务项目")
    private String serviceItems;
    /**
     * 服务套餐
     */
    @ExcelImport(value = "服务套餐",servicePackage = true)
    @ExcelExport(value = "服务套餐")
    private String servicePackage;
    /**
     * 协议价（元）
     */
    @ExcelImport(value = "协议价（元）",required = true,negotiatedPrice = true)
    @ExcelExport(value = "协议价（元）")
    private String negotiatedPrice;
    /**
     * 收费方式
     */
    @ExcelImport(value = "收费方式",required = true,chargingMethod = true)
    @ExcelExport(value = "收费方式")
    private String chargingMethod;
    /**
     * 核算类型
     */
    @ExcelImport(value = "核算类型",required = true)
    @ExcelExport(value = "核算类型")
    private String accountingType;

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
