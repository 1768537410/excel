package com.example.excel.entity;

import com.example.excel.util.ExcelExport;
import com.example.excel.util.ExcelImport;
import lombok.Data;

/**
 * C02 2表  客户账单生成条件
 * @author user
 */
@Data
public class CustomerBillGenerationConditions {

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
    @ExcelExport(value = "客户简称")
    private String customerAbbreviation;
    /**
     * 账单生成条件名称
     */
    @ExcelImport(value = "账单生成条件名称",required = true,unique = true)
    @ExcelExport(value = "账单生成条件名称")
    private String billGenerationConditionName;
    /**
     * 账单生成条件描述
     */
    @ExcelImport(value = "账单生成条件描述")
    @ExcelExport(value = "账单生成条件描述")
    private String descriptionBillGenerationConditions;
    /**
     * 社保费用
     */
    @ExcelImport(value = "社保费用",required = true,Serve = true)
    @ExcelExport(value = "社保费用")
    private String socialSecurityFee;
    /**
     * 医保费用
     */
    @ExcelImport(value = "医保费用",required = true,Serve = true)
    @ExcelExport(value = "医保费用")
    private String medicalInsuranceCosts;
    /**
     * 工伤费用
     */
    @ExcelImport(value = "工伤费用",required = true,Serve = true)
    @ExcelExport(value = "工伤费用")
    private String workInjuryExpenses;
    /**
     * 公积金费用
     */
    @ExcelImport(value = "公积金费用",required = true,Serve = true)
    @ExcelExport(value = "公积金费用")
    private String providentFundFee;
    /**
     * 商业保险费用
     */
    @ExcelImport(value = "商业保险费用",required = true,Serve = true)
    @ExcelExport(value = "商业保险费用")
    private String commercialInsuranceCosts;
    /**
     * 薪资费用
     */
    @ExcelImport(value = "薪资费用",required = true,Serve = true)
    @ExcelExport(value = "薪资费用")
    private String salaryCosts;
    /**
     * 工会费用
     */
    @ExcelImport(value = "工会费用",required = true,Serve = true)
    @ExcelExport(value = "工会费用")
    private String unionFees;
    /**
     * 残保金费用
     */
    @ExcelImport(value = "残保金费用",required = true,Serve = true)
    @ExcelExport(value = "残保金费用")
    private String disabilityInsuranceFee;
    /**
     * 弹性福利费用
     */
    @ExcelImport(value = "弹性福利费用",required = true,Serve = true)
    @ExcelExport(value = "弹性福利费用")
    private String charges;
    /**
     * 服务费
     */
    @ExcelImport(value = "服务费",required = true,Serve = true)
    @ExcelExport(value = "服务费")
    private String serviceCharge;
    /**
     * 代报税费用
     */
    @ExcelImport(value = "代报税费用",required = true,Serve = true)
    @ExcelExport(value = "代报税费用")
    private String taxFilingFee;
    /**
     * 车辆租赁费用
     */
    @ExcelImport(value = "车辆租赁费用",required = true,Serve = true)
    @ExcelExport(value = "车辆租赁费用")
    private String vehicleRentalFee;
    /**
     * 一次性费用
     */
    @ExcelImport(value = "一次性费用",required = true,Serve = true)
    @ExcelExport(value = "一次性费用")
    private String oneTimeFee;
    /**
     * 其他费用
     */
    @ExcelImport(value = "其他费用",required = true,Serve = true)
    @ExcelExport(value = "其他费用")
    private String otherCosts;

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
