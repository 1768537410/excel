package com.example.excel.entity;

import com.example.excel.util.ExcelExport;
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
    @ExcelImport(value = "合同名称",required = true,C08contractTitle = true)
    @ExcelExport(value = "合同名称")
    private String contractTitle;

    /**
     * 服务协议名称
     */
    @ExcelImport(value = "服务协议名称",required = true,C08serviceAgreementName = true)
    @ExcelExport(value = "服务协议名称")
    private String serviceAgreementName;

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
