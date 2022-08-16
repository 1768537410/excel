package com.example.excel.entity;

import com.example.excel.util.ExcelImport;
import lombok.Data;

/**
 * C05
 * @author zjc
 */
@Data
public class ServiceagreementBasicinformation {

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
    @ExcelImport(value = "服务协议名称",unique = true,required = true)
    private String serviceAgreementName;
    /**
     * 适用开始时间
     */
    @ExcelImport(value = "适用开始时间",dateTime = true,required = true)
    private String applicableStartTime;
    /**
     * 客户简称
     */
    @ExcelImport(value = "客户简称",required = true,dataCompliance = true)
    private String customerAbbreviation;
    /**
     * 客户编号
     */
    @ExcelImport(value = "客户编号",required = true,customerNumber = true)
    private String customerNumber;
    /**
     * 子客户编号
     */
    @ExcelImport(value = "子客户编号",customerNumber04 = true)
    private String subcustomerNumber;
    /**
     * 子客户简称
     */
    @ExcelImport(value = "子客户简称",dataCompliance04 = true)
    private String subcustomerAbbreviation;
    /**
     * 状态
     */
    @ExcelImport(value = "状态",required = true)
    private String status;
    /**
     * 备注
     */
    @ExcelImport("备注")
    private String remarks;
    /**
     * 按整体费用和比例收取服务费（0-否，1-是）
     */
    @ExcelImport(value = "按整体费用和比例收取服务费",required = true,co5TF = true,kv = "0-否;1-是")
    private String chargeServiceFee;
    /**
     * 社保费用
     */
    @ExcelImport(value = "社保费用",c05required = true)
    private String socialSecurityFee;
    /**
     * 医保费用
     */
    @ExcelImport(value = "医保费用",c05required = true)
    private String medicalInsuranceCosts;
    /**
     * 工伤费用
     */
    @ExcelImport(value = "工伤费用",c05required = true)
    private String workInjuryExpenses;
    /**
     * 公积金费用
     */
    @ExcelImport(value = "公积金费用",c05required = true)
    private String providentFundFee;
    /**
     * 商业保险费用
     */
    @ExcelImport(value = "商业保险费用",c05required = true)
    private String commercialInsuranceCosts;
    /**
     * 薪资费用
     */
    @ExcelImport(value = "薪资费用",c05required = true)
    private String salaryCosts;
    /**
     * 工会费用
     */
    @ExcelImport(value = "工会费用",c05required = true)
    private String unionFees;
    /**
     * 残保金费用
     */
    @ExcelImport(value = "残保金费用",c05required = true)
    private String disabilityInsuranceFee;
    /**
     * 弹性福利费用
     */
    @ExcelImport(value = "弹性福利费用",c05required = true)
    private String charges;
    /**
     * 服务费
     */
    @ExcelImport(value = "服务费",c05required = true)
    private String serviceCharge;
    /**
     * 代报税费用
     */
    @ExcelImport(value = "代报税费用",c05required = true)
    private String taxFilingFee;
    /**
     * 车辆租赁费用
     */
    @ExcelImport(value = "车辆租赁费用",c05required = true)
    private String vehicleRentalFee;
    /**
     * 一次性费用
     */
    @ExcelImport(value = "一次性费用",c05required = true)
    private String oneTimeFee;
    /**
     * 其他费用
     */
    @ExcelImport(value = "其他费用",c05required = true)
    private String otherCosts;
    /**
     * 比例(%)
     */
    @ExcelImport(value = "比例(%)",c05required = true)
    private String proportion;
    /**
     * 收费方式
     */
    @ExcelImport(value = "收费方式",c05required = true)
    private String chargingMethod;
    /**
     * 服务协议编号
     */
    @ExcelImport("服务协议编号")
    private String serviceAgreementNumber;
    /**
     * 错误提示
     */
    private String rowTips;
    /**
     * 原始数据
     */
    private String  rowData;

}
