package com.example.excel.entity;

import com.example.excel.util.ExcelExport;
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
    @ExcelExport(value = "服务协议名称")
    private String serviceAgreementName;
    /**
     * 适用开始时间
     */
    @ExcelImport(value = "适用开始时间",dateTime = true,required = true)
    @ExcelExport(value = "适用开始时间")
    private String applicableStartTime;
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
     * 子客户编号
     */
    @ExcelImport(value = "子客户编号",customerNumber04 = true)
    @ExcelExport(value = "子客户编号")
    private String subcustomerNumber;
    /**
     * 子客户简称
     */
    @ExcelImport(value = "子客户简称",dataCompliance04 = true)
    @ExcelExport(value = "子客户简称")
    private String subcustomerAbbreviation;
    /**
     * 状态
     */
    @ExcelImport(value = "状态",required = true)
    @ExcelExport(value = "状态")
    private String status;
    /**
     * 备注
     */
    @ExcelImport("备注")
    @ExcelExport(value = "备注")
    private String remarks;
    /**
     * 按整体费用和比例收取服务费（0-否，1-是）
     */
    @ExcelImport(value = "按整体费用和比例收取服务费",required = true,co5TF = true,kv = "0-否;1-是")
    @ExcelExport(value = "按整体费用和比例收取服务费")
    private String chargeServiceFee;
    /**
     * 社保费用
     */
    @ExcelImport(value = "社保费用",c05required = true)
    @ExcelExport(value = "社保费用")
    private String socialSecurityFee;
    /**
     * 医保费用
     */
    @ExcelImport(value = "医保费用",c05required = true)
    @ExcelExport(value = "医保费用")
    private String medicalInsuranceCosts;
    /**
     * 工伤费用
     */
    @ExcelImport(value = "工伤费用",c05required = true)
    @ExcelExport(value = "工伤费用")
    private String workInjuryExpenses;
    /**
     * 公积金费用
     */
    @ExcelImport(value = "公积金费用",c05required = true)
    @ExcelExport(value = "公积金费用")
    private String providentFundFee;
    /**
     * 商业保险费用
     */
    @ExcelImport(value = "商业保险费用",c05required = true)
    @ExcelExport(value = "商业保险费用")
    private String commercialInsuranceCosts;
    /**
     * 薪资费用
     */
    @ExcelImport(value = "薪资费用",c05required = true)
    @ExcelExport(value = "薪资费用")
    private String salaryCosts;
    /**
     * 工会费用
     */
    @ExcelImport(value = "工会费用",c05required = true)
    @ExcelExport(value = "工会费用")
    private String unionFees;
    /**
     * 残保金费用
     */
    @ExcelImport(value = "残保金费用",c05required = true)
    @ExcelExport(value = "残保金费用")
    private String disabilityInsuranceFee;
    /**
     * 弹性福利费用
     */
    @ExcelImport(value = "弹性福利费用",c05required = true)
    @ExcelExport(value = "弹性福利费用")
    private String charges;
    /**
     * 服务费
     */
    @ExcelImport(value = "服务费",c05required = true)
    @ExcelExport(value = "服务费")
    private String serviceCharge;
    /**
     * 代报税费用
     */
    @ExcelImport(value = "代报税费用",c05required = true)
    @ExcelExport(value = "代报税费用")
    private String taxFilingFee;
    /**
     * 车辆租赁费用
     */
    @ExcelImport(value = "车辆租赁费用",c05required = true)
    @ExcelExport(value = "车辆租赁费用")
    private String vehicleRentalFee;
    /**
     * 一次性费用
     */
    @ExcelImport(value = "一次性费用",c05required = true)
    @ExcelExport(value = "一次性费用")
    private String oneTimeFee;
    /**
     * 其他费用
     */
    @ExcelImport(value = "其他费用",c05required = true)
    @ExcelExport(value = "其他费用")
    private String otherCosts;
    /**
     * 比例(%)
     */
    @ExcelImport(value = "比例(%)",c05required = true)
    @ExcelExport(value = "比例(%)")
    private String proportion;
    /**
     * 收费方式
     */
    @ExcelImport(value = "收费方式",c05required = true)
    @ExcelExport(value = "收费方式")
    private String chargingMethod;
    /**
     * 服务协议编号
     */
    @ExcelImport("服务协议编号")
    @ExcelExport(value = "服务协议编号")
    private String serviceAgreementNumber;
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
