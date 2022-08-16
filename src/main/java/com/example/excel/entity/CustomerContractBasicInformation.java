package com.example.excel.entity;

import com.example.excel.util.ExcelImport;
import lombok.Data;

/**
 * C07
 * @author user
 */
@Data
public class CustomerContractBasicInformation {

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
    private String customerAbbreviation;
    /**
     * 合同名称
     */
    @ExcelImport(value = "合同名称",required = true,unique = true,contractTitle = true)
    private String contractTitle;
    /**
     * 合同类型
     */
    @ExcelImport(value = "合同类型",required = true)
    private String contractType;
    /**
     * 合同版本
     */
    @ExcelImport(value = "合同版本",required = true)
    private String contractVersion;
    /**
     * 合同/协议级别
     */
    @ExcelImport(value = "合同/协议级别",required = true,contractLevel = true)
    private String contractLevel;
    /**
     * 一级合同名称
     */
    @ExcelImport(value = "一级合同名称",firstContractName = true)
    private String firstContractName;
    /**
     * 账单生成条件
     */
    @ExcelImport(value = "账单生成条件",required = true,billGenerationConditionName = true)
    private String generationConditions;
    /**
     * 签订类型
     */
    @ExcelImport(value = "签订类型",required = true)
    private String signType;
    /**
     * 签订时间
     */
    @ExcelImport(value = "签订时间",required = true,dateTime = true)
    private String signingTime;
    /**
     * 合同开始时间
     */
    @ExcelImport(value = "合同开始时间",required = true,dateTime = true)
    private String contractStartTime;
    /**
     * 合同终止时间
     */
    @ExcelImport(value = "合同终止时间",required = true,dateTime = true)
    private String contractTermination;
    /**
     * 合同状态
     */
    @ExcelImport(value = "合同状态",required = true,co7TF = true)
    private String contractStatus;
    /**
     * 终止方式
     */
    @ExcelImport(value = "终止方式",c07required = true)
    private String terminationMethod;
    /**
     * 终止日期
     */
    @ExcelImport(value = "终止日期",dateTime = true,c07required = true)
    private String endDate;
    /**
     * 末次费用收取时间
     */
    @ExcelImport(value = "末次费用收取时间",dateTime = true,c07required = true)
    private String lastFeeCollectionTime;
    /**
     * 终止原因
     */
    @ExcelImport(value = "终止原因",c07required = true)
    private String reasonTermination;
    /**
     * 签订次数
     */
    @ExcelImport(value = "签订次数",required = true)
    private String numberSignings;
    /**
     *签订份数
     */
    @ExcelImport(value = "签订份数")
    private String numberCopiesSigned;
    /**
     * 前合同名称
     */
    @ExcelImport(value = "前合同名称")
    private String formerContractName;
    /**
     * 合同到期提醒天数
     */
    @ExcelImport(value = "合同到期提醒天数",required = true)
    private String contractExpirationReminderDays;
    /**
     * 月账单生成时间
     */
    @ExcelImport(value = "月账单生成时间",required = true)
    private String monthlyBillGenerationTime;
    /**
     * 年账单生成时间
     */
    @ExcelImport(value = "年账单生成时间",dateTime2 = true)
    private String annualBillGenerationTime;
    /**
     * 工资发放日
     */
    @ExcelImport(value = "工资发放日")
    private String payday;
    /**
     * 约定付款日（月付）
     */
    @ExcelImport(value = "约定付款日（月付）")
    private String agreedPaymentDate;
    /**
     * 约定付款月日（年付）
     */
    @ExcelImport(value = "约定付款月日（年付）",dateTime2 = true)
    private String agreedPaymentMonthAndDay;
    /**
     * 账号
     */
    @ExcelImport(value = "账号")
    private String account;
    /**
     * 开户银行
     */
    @ExcelImport(value = "开户银行")
    private String depositaryBank;
    /**
     * 银行开户名称
     */
    @ExcelImport(value = "银行开户名称")
    private String bankAccountName;
    /**
     * 是否包含退回条款
     */
    @ExcelImport(value = "是否包含退回条款",required = true)
    private String returnTerms;
    /**
     * 是否包含工伤条款
     */
    @ExcelImport(value = "是否包含工伤条款",required = true)
    private String workLnjuryClause;
    /**
     * 是否包含退休条款
     */
    @ExcelImport(value = "是否包含退休条款",required = true)
    private String retirementClause;
    /**
     * 是否包含司机条款
     */
    @ExcelImport(value = "是否包含司机条款",required = true)
    private String driverTerms;
    /**
     * 是否包含职业病条款
     */
    @ExcelImport(value = "是否包含职业病条款",required = true)
    private String occupationalDiseaseClause;
    /**
     * 是否包含基数条款
     */
    @ExcelImport(value = "是否包含基数条款",required = true)
    private String cardinalTerms;
    /**
     *转出状态
     */
    @ExcelImport(value = "转出状态",required = true)
    private String rolloutStatus;
    /**
     * 归档状态
     */
    @ExcelImport(value = "归档状态",required = true)
    private String archiveStatus;
    /**
     * 是否有自动顺延条款
     */
    @ExcelImport(value = "是否有自动顺延条款",required = true)
    private String automaticExtensionClause;
    /**
     * 其他特殊条款说明
     */
    @ExcelImport(value = "其他特殊条款说明")
    private String otherPecialTermsAndConditions;
    /**
     * 备注
     */
    @ExcelImport(value = "备注")
    private String remark;
    /**
     * 联系人1
     */
    @ExcelImport(value = "联系人1",required = true)
    private String contactOne;
    /**
     * 联系人1电话
     */
    @ExcelImport("联系人1电话")
    private String contactPhoneOne;
    /**
     * 联系人1手机
     */
    @ExcelImport(value = "联系人1手机",required = true)
    private String contactMobileOne;
    /**
     * 联系人1邮箱
     */
    @ExcelImport(value = "联系人1邮箱",required = true)
    private String contactEmailOne;
    /**
     *联系人1邮寄地址
     */
    @ExcelImport(value = "联系人1邮寄地址",required = true)
    private String contactMailingDdressOne;
    /**
     * 联系人1备注
     */
    @ExcelImport("联系人1备注")
    private String contactNoteOne;
    /**
     * 联系人2
     */
    @ExcelImport("联系人2")
    private String contactTwo;
    /**
     * 联系人2电话
     */
    @ExcelImport("联系人2电话")
    private String contactPhoneTwo;
    /**
     * 联系人2手机
     */
    @ExcelImport("联系人2手机")
    private String contactMobileTwo;
    /**
     * 联系人2邮箱
     */
    @ExcelImport("联系人2邮箱")
    private String contactEmailTwo;
    /**
     *联系人2邮寄地址
     */
    @ExcelImport("联系人2邮寄地址")
    private String contactMailingDdressTwo;
    /**
     * 联系人2备注
     */
    @ExcelImport("联系人2备注")
    private String contactNoteTwo;
    /**
     * 合同编号
     */
    @ExcelImport("合同编号")
    private String contractNumber;
    /**
     * 未到账提醒天数
     */
    @ExcelImport("未到账提醒天数")
    private String reminderNotReceipt;
    /**
     * 错误提示
     */
    private String rowTips;
    /**
     * 原始数据
     */
    private String  rowData;
}
