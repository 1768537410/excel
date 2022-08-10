package com.example.excel.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangjuncheng
 * @date 2021/12/17
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelImport {

    /**
     *  字段名称
     */
    String value();

    /**
     * 导出映射，格式如：0-未知;1-男;2-女
     * @return
     */
    String kv() default "";

    /**
     * 是否为必填字段（默认为非必填）
     * @return
     */
    boolean required() default false;

    /**
     * 最大长度（默认255）
     * @return
     */
    int maxLength() default 255;

    /**
     * 导入唯一性验证（多个字段则取联合验证）
     * @return
     */
    boolean unique() default false;

    /**
     * 不允许包含关键字省
     * @return
     */
    String notKeyWord() default "";

    /**
     * 填写格式是否规范（需要填写对应岗位） 业务二部专员1
     */
    boolean departmentCommissioner() default false;

    /**
     * 判断日期格式是否规范 2019-08-23
     */
    boolean dateTime() default false;



}
