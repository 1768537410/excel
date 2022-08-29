package com.example.excel.util.Vo;

import lombok.Getter;

/**
 * 响应码枚举
 */
@Getter
public enum ResultCode {
    /**
     * 操作成功
     */
    SUCCESS(1000, "操作成功"),
    /**
     * 响应失败
     */
    FAILED(1001, "响应失败"),
    /**
     * 参数校验失败
     */
    VALIDATE_FAILED(1002, "参数校验失败"),
    /**
     * 未查询到相关信息
     */
    NO_RESULT(1003, "未查询到相关信息"),
    /**
     * 未查询到相关信息
     */
    MES_ERROR(1004, "未查询到相关信息"),
    /**
     * 无权限
     */
    NO_AUTHORITY(1005, "无权限"),
    /**
     * 数据已存在
     */
    DATA_EXIST(1006, "数据已存在"),
    /**
     * 未知错误
     */
    ERROR(5000, "未知错误"),
    /**
     * 文件上传失败
     */
    FILE_UPLOAD_ERROR(5001, "文件上传失败");


    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}


