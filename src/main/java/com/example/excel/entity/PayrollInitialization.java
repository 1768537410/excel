package com.example.excel.entity;

import com.example.excel.util.ExcelExport;
import com.example.excel.util.ExcelImport;
import lombok.Data;

/**
 * C016
 * @author user
 */
@Data
public class PayrollInitialization {

    /**
     * 获取行号
     */
    private double rowNum;
    /**
     * 主键
     */
    private int id;
    /**
     * 姓名
     */
    @ExcelImport(value = "姓名",required = true)
    @ExcelExport(value = "姓名")
    private String name;
    /**
     * 证件类型
     */
    @ExcelImport(value = "证件类型",required = true)
    @ExcelExport(value = "证件类型")
    private String certificateType;
    /**
     * 证件号码
     */
    @ExcelImport(value = "证件号码",required = true)
    @ExcelExport(value = "证件号码")
    private String idNumber;
    /**
     * 税款所属月份
     */
    @ExcelImport(value = "税款所属月份",required = true,dateTime2 = true)
    @ExcelExport(value = "税款所属月份")
    private String monthTax;
    /**
     * 所得项目
     */
    @ExcelImport(value = "所得项目",required = true)
    @ExcelExport(value = "所得项目")
    private String incomeItem;
    /**
     *任职受雇从业类型
     */
    @ExcelImport(value = "任职受雇从业类型",required = true)
    @ExcelExport(value = "任职受雇从业类型")
    private String employmentType;
    /**
     *以前月累计应发合计
     */
    @ExcelImport(value = "以前月累计应发合计",c016required = true)
    @ExcelExport(value = "以前月累计应发合计")
    private String cumulativeAmountTotalPayable;
    /**
     *以前月累计其他计税项
     */
    @ExcelImport(value = "以前月累计其他计税项",c016required = true)
    @ExcelExport(value = "以前月累计其他计税项")
    private String accumulatedAmountOtherTax;
    /**
     *以前月累计收入
     */
    @ExcelImport(value = "以前月累计收入",c016required = true)
    @ExcelExport(value = "以前月累计收入")
    private String cumulativeAmountIncome;
    /**
     *以前月累计免税收入
     */
    @ExcelImport(value = "以前月累计免税收入",c016required = true)
    @ExcelExport(value = "以前月累计免税收入")
    private String accumulatedAmountTaxFreeIncome;
    /**
     *以前月累计费用
     */
    @ExcelImport(value = "以前月累计费用",c016required = true)
    @ExcelExport(value = "以前月累计费用")
    private String cumulativeAmountCharges;
    /**
     *以前月累计减除费用
     */
    @ExcelImport(value = "以前月累计减除费用",c016required = true)
    @ExcelExport(value = "以前月累计减除费用")
    private String cumulativeAmountDeductions;
    /**
     *以前月累计专项扣除
     */
    @ExcelImport(value = "以前月累计专项扣除",c016required = true)
    @ExcelExport(value = "以前月累计专项扣除")
    private String cumulativeAmountSpecial;
    /**
     *以前月累计其他扣除
     */
    @ExcelImport(value = "以前月累计其他扣除",c016required = true)
    @ExcelExport(value = "以前月累计其他扣除")
    private String accumulatedAmountOtherDeductions;
    /**
     *以前月累计税后减项
     */
    @ExcelImport(value = "以前月累计税后减项",c016required = true)
    @ExcelExport(value = "以前月累计税后减项")
    private String accumulatedAfterAmountTax;
    /**
     *以前月累计税后应客户要求扣款项
     */
    @ExcelImport(value = "以前月累计税后应客户要求扣款项",c016required = true)
    @ExcelExport(value = "以前月累计税后应客户要求扣款项")
    private String debitAmount;
    /**
     *以前月累计应纳税所得额
     */
    @ExcelImport(value = "以前月累计应纳税所得额",c016required = true)
    @ExcelExport(value = "以前月累计应纳税所得额")
    private String accumulatedTaxableAmountIncome;
    /**
     *以前月累计应纳税额
     */
    @ExcelImport(value = "以前月累计应纳税额",c016required = true)
    @ExcelExport(value = "以前月累计应纳税额")
    private String accumulatedTaxAmountPayable;
    /**
     *以前月累计减免税额
     */
    @ExcelImport(value = "以前月累计减免税额",c016required = true)
    @ExcelExport(value = "以前月累计减免税额")
    private String cumulativeTaxAmountDeduction;
    /**
     *以前月累计已缴税额
     */
    @ExcelImport(value = "以前月累计已缴税额",c016required = true)
    @ExcelExport(value = "以前月累计已缴税额")
    private String accumulatedTaxAmountPaid;
    /**
     *以前月累计实发工资
     */
    @ExcelImport(value = "以前月累计实发工资",c016required = true)
    @ExcelExport(value = "以前月累计实发工资")
    private String cumulativePaidAmountWages;
    /**
     *以前月累计银行实发工资
     */
    @ExcelImport(value = "以前月累计银行实发工资",c016required = true)
    @ExcelExport(value = "以前月累计银行实发工资")
    private String accumulatedBankAmountSalary;
    /**
     *以前月累计个人欠款
     */
    @ExcelImport(value = "以前月累计个人欠款",c016required = true)
    @ExcelExport(value = "以前月累计个人欠款")
    private String accumulatedPersonalAmountDebt;
    /**
     *以前月累计子女教育
     */
    @ExcelImport(value = "以前月累计子女教育",c016required = true)
    @ExcelExport(value = "以前月累计子女教育")
    private String cumulativeChildrenAmountEducation;
    /**
     *以前月累计房贷款利息
     */
    @ExcelImport(value = "以前月累计房贷款利息",c016required = true)
    @ExcelExport(value = "以前月累计房贷款利息")
    private String accumulatedMortgageAmountInterest;
    /**
     *以前月累计住房租金
     */
    @ExcelImport(value = "以前月累计住房租金",c016required = true)
    @ExcelExport(value = "以前月累计住房租金")
    private String cumulativeHousingAmountRent;
    /**
     *以前月累计赡养老人
     */
    @ExcelImport(value = "以前月累计赡养老人",c016required = true)
    @ExcelExport(value = "以前月累计赡养老人")
    private String cumulativeSupportAmountElderly;
    /**
     *以前月累计继续教育
     */
    @ExcelImport(value = "以前月累计继续教育",c016required = true)
    @ExcelExport(value = "以前月累计继续教育")
    private String cumulativeAmountContinuingEducation;
    /**
     *以前月累计专项附加扣除合计
     */
    @ExcelImport(value = "以前月累计专项附加扣除合计",c016required = true)
    @ExcelExport(value = "以前月累计专项附加扣除合计")
    private String specialAdditionalAmountDeduction;
    /**
     *累计应发合计
     */
    @ExcelImport(value = "累计应发合计",c016required = true)
    @ExcelExport(value = "累计应发合计")
    private String cumulativeTotalPayable;
    /**
     *累计其他计税项
     */
    @ExcelImport(value = "累计其他计税项",c016required = true)
    @ExcelExport(value = "累计其他计税项")
    private String accumulatedOtherTax;
    /**
     *累计收入
     */
    @ExcelImport(value = "累计收入",c016required = true)
    @ExcelExport(value = "累计收入")
    private String cumulativeIncome;
    /**
     *累计免税收入
     */
    @ExcelImport(value = "累计免税收入",c016required = true)
    @ExcelExport(value = "累计免税收入")
    private String accumulatedTaxFreeIncome;
    /**
     *累计费用
     */
    @ExcelImport(value = "累计费用",c016required = true)
    @ExcelExport(value = "累计费用")
    private String cumulativeCharges;
    /**
     *累计减除费用
     */
    @ExcelImport(value = "累计减除费用",c016required = true)
    @ExcelExport(value = "累计减除费用")
    private String cumulativeDeductions;
    /**
     *累计专项扣除
     */
    @ExcelImport(value = "累计专项扣除",c016required = true)
    @ExcelExport(value = "累计专项扣除")
    private String cumulativeSpecialDeduction;
    /**
     *累计其他扣除
     */
    @ExcelImport(value = "累计其他扣除",c016required = true)
    @ExcelExport(value = "累计其他扣除")
    private String accumulatedOtherDeductions;
    /**
     *累计税后减项
     */
    @ExcelImport(value = "累计税后减项",c016required = true)
    @ExcelExport(value = "累计税后减项")
    private String accumulatedAfterTaxDeductions;
    /**
     *累计税后应客户要求扣款项
     */
    @ExcelImport(value = "累计税后应客户要求扣款项",c016required = true)
    @ExcelExport(value = "累计税后应客户要求扣款项")
    private String customerRequestsDeduction;
    /**
     *累计应纳税所得额
     */
    @ExcelImport(value = "累计应纳税所得额",c016required = true)
    @ExcelExport(value = "累计应纳税所得额")
    private String accumulatedTaxableIncome;
    /**
     *累计应纳税额
     */
    @ExcelImport(value = "累计应纳税额",c016required = true)
    @ExcelExport(value = "累计应纳税额")
    private String accumulatedTaxPayable;
    /**
     *累计减免税额
     */
    @ExcelImport(value = "累计减免税额",c016required = true)
    @ExcelExport(value = "累计减免税额")
    private String cumulativeTaxDeduction;
    /**
     *累计应扣缴税额
     */
    @ExcelImport(value = "累计应扣缴税额",c016required = true)
    @ExcelExport(value = "累计应扣缴税额")
    private String accumulatedTaxWithholding;
    /**
     *累计已缴税额
     */
    @ExcelImport(value = "累计已缴税额",c016required = true)
    @ExcelExport(value = "累计已缴税额")
    private String accumulatedTaxPaid;
    /**
     *累计实发工资
     */
    @ExcelImport(value = "累计实发工资",c016required = true)
    @ExcelExport(value = "累计实发工资")
    private String cumulativePaidWages;
    /**
     *累计银行实发工资
     */
    @ExcelImport(value = "累计银行实发工资",c016required = true)
    @ExcelExport(value = "累计银行实发工资")
    private String accumulatedBankSalary;
    /**
     *累计个人欠款
     */
    @ExcelImport(value = "累计个人欠款",c016required = true)
    @ExcelExport(value = "累计个人欠款")
    private String accumulatedPersonalDebt;
    /**
     *薪资所属月份
     */
    @ExcelImport(value = "薪资所属月份",c016required = true)
    @ExcelExport(value = "薪资所属月份")
    private String monthSalary;
    /**
     *客户简称
     */
    @ExcelImport(value = "客户简称",required = true)
    @ExcelExport(value = "客户简称")
    private String customerAbbreviation;
    /**
     *子客户简称
     */
    @ExcelImport(value = "子客户简称")
    @ExcelExport(value = "子客户简称")
    private String subCustomerAbbreviation;
    /**
     *报税名义
     */
    @ExcelImport(value = "报税名义",required = true)
    @ExcelExport(value = "报税名义")
    private String taxReturnName;
    /**
     *纳税企业名称
     */
    @ExcelImport(value = "纳税企业名称",required = true)
    @ExcelExport(value = "纳税企业名称")
    private String taxCompanyName;
    /**
     *客户薪资模板名称
     */
    @ExcelImport(value = "客户薪资模板名称",required = true)
    @ExcelExport(value = "客户薪资模板名称")
    private String customerSalaryTemplate;
    /**
     *纳税姓名
     */
    @ExcelImport(value = "纳税姓名",required = true)
    @ExcelExport(value = "纳税姓名")
    private String axName;
    /**
     *纳税证件类型
     */
    @ExcelImport(value = "纳税证件类型",required = true)
    @ExcelExport(value = "纳税证件类型")
    private String taxDocumentType;
    /**
     *纳税证照号码
     */
    @ExcelImport(value = "纳税证照号码",required = true)
    @ExcelExport(value = "纳税证照号码")
    private String taxLicenseNumber;
    /**
     *入职年度就业情形
     */
    @ExcelImport(value = "入职年度就业情形")
    @ExcelExport(value = "入职年度就业情形")
    private String employmentSituation;

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
