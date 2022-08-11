package com.example.excel.entity;

import com.example.excel.util.ExcelImport;
import lombok.Data;

/**
 * @author zjc
 * C03
 */
@Data
public class CustomerSettlementUnit {
    /**
     * 获取行号
     */
    private double rowNum;


    /**
     * 主键
     */
    private int id;

    /**
     *统一社会信用代码/纳税人识别号
     */
    @ExcelImport(value = "统一社会信用代码/纳税人识别号",required = true)
    private String axpayerIdentificationNumber;
    /**
     * 联系人姓名
     */
    @ExcelImport(value = "联系人姓名",required = true)
    private String contactName;
    /**
     * 联系人邮箱
     */
    @ExcelImport(value = "联系人邮箱",required = true)
    private String contactEmail;
    /**
     * 自动认单来款数量
     */
    @ExcelImport("自动认单来款数量")
    private String amountPayment;
    /**
     * 是否来款开票
     */
    @ExcelImport("是否来款开票")
    private String incomingInvoice;
    /**
     * 备注
     */
    @ExcelImport("备注")
    private String remarks;
    /**
     * 客户限额
     */
    @ExcelImport("客户限额")
    private String customerLimit;
    /**
     * 实际客户余额
     */
    @ExcelImport(value = "实际客户余额",required = true)
    private String clientBalance;
    /**
     * 客户垫款周期
     */
    @ExcelImport("客户垫款周期")
    private String advanceCycle;

    /**
     * 客户简称
     */
    @ExcelImport(value = "客户简称",required = true,dataCompliance = true)
    private String customerAbbreviation;
    /**
     * 结算单位编号
     */
    @ExcelImport(value = "结算单位编号",required = true)
    private String clearingUnitNumber;
    /**
     * 结算单位名称
     */
    @ExcelImport(value = "结算单位名称",required = true)
    private String settlementUnitName;
    /**
     * 错误提示
     */
    private String rowTips;
    /**
     * 原始数据
     */
    private String  rowData;
}
