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

    /**
     * 判断日期格式是否规范 2019-08
     */
    boolean dateTime2() default false;

    /**
     * 客户简称 是否与C01中的客户简称相等
     */
    boolean dataCompliance() default false;

    /**
     *  客户编号 是否与C01中的客户编号相等
     * @return
     */
    boolean customerNumber() default false;

    /**
     * 客户简称 是否与C04中的子客户简称相等
     */
    boolean dataCompliance04() default false;

    /**
     *  客户编号 是否与C04中的子客户编号相等
     * @return
     */
    boolean customerNumber04() default false;

    /**
     * 判断C05中 按整体费用和比例收取服务费 的 是 否
     * @return
     */
    boolean co5TF() default false;

    /**
     * 必填方法
     * 与 co5TF() 配合使用  当co5TF()判断内容为是的时候触发
     * @return
     */
    boolean c05required() default false;

    /**
     * 获取C06 一列服务协议名称
     * @return
     */
    boolean ListServiceAgreementNameBuilder() default false;

    /**
     * 获取C06 服务产品
     * @return
     */
    boolean serviceProducts() default false;

    /**
     * 获取C06 服务项目 C-06的“服务项目“注意“薪资核算”和“薪资核算与发放”只能选择一个
     * @return
     */

    boolean serviceItems() default false;
    /**
     * 获取C06 服务套餐
     * @return
     */
    boolean servicePackage() default false;

    /**
     * 获取C06 收费方式
     * @return
     */
    boolean chargingMethod() default false;

    /**
     * 获取C06 一列协议价
     * @return
     */
    boolean negotiatedPrice() default false;

    //C02 2表 如员工没有该项服务，账单生成条件选择“人工核算”，不能留空

    /**
     * C02 2表 如员工没有该项服务，账单生成条件选择“人工核算”，不能留空
     * @return
     */
    boolean Serve() default false;

    /**
     * C-07中账单生成条件必须与C-02 2表一致
     */
    boolean billGenerationConditionName() default false;

    //C07  合同/协议级别为二级或者三级的，需填写所属的一级合同名称

    /**
     * 获取 C07 合同名称
     * @return
     */
    boolean contractTitle() default false;

    /**
     * 获取C07 合同/协议级别
     */
    boolean contractLevel() default false;

    /**
     * 获取C07 一级合同名称
     * @return
     */
    boolean firstContractName() default false;

    /**
     * C07 当合同状态为“已失效”时，终止方式、终止日期、末次费用收取时间、终止原因，必填
     * @return
     */
    boolean co7TF() default false;

    /**
     * 必填方法 与 co7TF() 配合使用  当co7TF()判断内容为是的时候触发
     * @return
     */
    boolean c07required() default false;



}
