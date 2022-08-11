package com.example.excel.entity;

import lombok.Data;

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
     * 错误提示
     */
    private String rowTips;
    /**
     * 原始数据
     */
    private String  rowData;

}
