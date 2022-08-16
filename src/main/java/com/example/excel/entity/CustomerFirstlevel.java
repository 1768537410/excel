package com.example.excel.entity;

import com.example.excel.util.ExcelImport;
import lombok.Data;

/**
 * C08
 * @author user
 */
@Data
public class CustomerFirstlevel {

    /**
     * 获取行号
     */
    private double rowNum;
    /**
     * 主键
     */
    private int id;
    /**
     * 合同名称
     */
    @ExcelImport(value = "合同名称",required = true)
    private String contractTitle;

    /**
     * 服务协议名称
     */
    @ExcelImport(value = "服务协议名称",required = true)
    private String serviceAgreementName;

    /**
     * 错误提示
     */
    private String rowTips;
    /**
     * 原始数据
     */
    private String  rowData;
}
