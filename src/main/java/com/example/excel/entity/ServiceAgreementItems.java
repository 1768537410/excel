package com.example.excel.entity;

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
    private String serviceAgreementName;
    /**
     * 服务产品
     */
    @ExcelImport(value = "服务产品",serviceProducts = true)
    private String serviceProducts;
    /**
     * 服务项目
     */
    @ExcelImport(value = "服务项目",serviceItems = true)
    private String serviceItems;
    /**
     * 服务套餐
     */
    @ExcelImport(value = "服务套餐",servicePackage = true)
    private String servicePackage;
    /**
     * 协议价（元）
     */
    @ExcelImport(value = "协议价（元）",required = true,negotiatedPrice = true)
    private String negotiatedPrice;
    /**
     * 收费方式
     */
    @ExcelImport(value = "收费方式",required = true,chargingMethod = true)
    private String chargingMethod;
    /**
     * 核算类型
     */
    @ExcelImport(value = "核算类型",required = true)
    private String accountingType;

    /**
     * 错误提示
     */
    private String rowTips;
    /**
     * 原始数据
     */
    private String  rowData;
}
