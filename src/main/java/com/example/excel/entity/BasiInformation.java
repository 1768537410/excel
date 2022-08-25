package com.example.excel.entity;

import com.example.excel.util.ExcelExport;
import com.example.excel.util.ExcelImport;
import lombok.Data;

/**
 * C01
 */
@Data
public class BasiInformation {


    /**
     * 获取行号
     */
    private double rowNum;
    /**
     * 主键
     */
    private int id;
    /**
     * 客户全称
     */
    @ExcelImport(value = "客户全称",required = true,unique = true)
    @ExcelExport(value = "客户全称")
    private String customerName;
    /**
     * 客户简称
     */
    @ExcelImport(value = "客户简称",required = true,unique = true)
    @ExcelExport(value = "客户简称")
    private String customerAbbreviation;

    /**
     * 客户编号
     */
    @ExcelImport(value = "客户编号",required = true,unique = true)
    @ExcelExport(value = "客户编号")
    private String customerNumber;

    /**
     * 所属集团
     */
    @ExcelImport("所属集团")
    @ExcelExport(value = "所属集团")
    private String affiliatedGroup;
    /**
     * 企业性质
     */
    @ExcelImport(value = "企业性质",required = true)
    @ExcelExport(value = "企业性质")
    private String enterpriseNature;
    /**
     * 统一社会信用代码
     */
    @ExcelImport("统一社会信用代码")
    @ExcelExport(value = "统一社会信用代码")
    private String uniformSCCode;
    /**
     * 法定代表人
     */
    @ExcelImport("法定代表人")
    @ExcelExport(value = "法定代表人")
    private String legalRepresentative;
    /**
     * 注册资本
     */
    @ExcelImport("注册资本")
    @ExcelExport(value = "注册资本")
    private String registeredApital;
    /**
     * 经营范围
     */
    @ExcelImport("经营范围")
    @ExcelExport(value = "经营范围")
    private String businessScope;
    /**
     * 成立时间
     */
    @ExcelImport(value = "成立时间",dateTime = true)
    @ExcelExport(value = "成立时间")
    private String establishmentTime;
    /**
     * 营业期限
     */
    @ExcelImport("营业期限")
    @ExcelExport(value = "营业期限")
    private String businessPeriod;
    /**
     * 注册地址（省）
     */
    @ExcelImport(value = "注册地址（省）",notKeyWord = "省")
    @ExcelExport(value = "注册地址（省）")
    private String registeredProvince;
    /**
     * 注册地址（市）
     */
    @ExcelImport("注册地址（市）")
    @ExcelExport(value = "注册地址（市）")
    private String registeredCity;
    /**
     * 注册地址（县）
     */
    @ExcelImport("注册地址（县）")
    @ExcelExport(value = "注册地址（县）")
    private String registeredCounty;
    /**
     * 详细注册地址
     */
    @ExcelImport("详细注册地址")
    @ExcelExport(value = "详细注册地址")
    private String detailedAddress;
    /**
     * 是否得到执照复印件（0-否，1-是）
     */
    @ExcelImport(value = "是否得到执照复印件",kv = "0-否;1-是")
    @ExcelExport(value = "是否得到执照复印件")
    private String copyLicense;
    /**
     * 其他注册信息
     */
    @ExcelImport("其他注册信息")
    @ExcelExport(value = "其他注册信息")
    private String registrationInformation;
    /**
     * 办公地址（省）
     */
    @ExcelImport(value = "办公地址（省）",required = true,notKeyWord = "省")
    @ExcelExport(value = "办公地址（省）")
    private String officeProvince;
    /**
     * 办公地址（市）
     */
    @ExcelImport("办公地址（市）")
    @ExcelExport(value = "公地址（市）")
    private String officeCity;
    /**
     *办公地址（县）
     */
    @ExcelImport("办公地址（县）")
    @ExcelExport(value = "办公地址（县）")
    private String officeCounty;
    /**
     * 详细办公地址
     */
    @ExcelImport("详细办公地址")
    @ExcelExport(value = "详细办公地址")
    private String officeAddress;
    /**
     * 邮政编码
     */
    @ExcelImport("邮政编码")
    @ExcelExport(value = "邮政编码")
    private String zipCode;
    /**
     * 备注
     */
    @ExcelImport("备注")
    @ExcelExport(value = "备注")
    private String remarks;
    /**
     * 是否外地委托客户（0-否，1-是）
     */
    @ExcelImport(value = "是否外地委托客户",kv = "0-否;1-是",required = true)
    @ExcelExport(value = "是否外地委托客户")
    private String foreignEntrusted;
    /**
     * 业务部
     */
    @ExcelImport(value = "业务部",required = true)
    @ExcelExport(value = "业务部")
    private String businessDepartment;
    /**
     * 业务员
     */
    @ExcelImport(value = "业务员",required = true,departmentCommissioner = true)
    @ExcelExport(value = "业务员")
    private String salesperson;
    /**
     * 签约状态
     */
    @ExcelImport(value = "签约状态",required = true)
    @ExcelExport(value = "签约状态")
    private String contractStatus;
    /**
     * 联系人1
     */
    @ExcelImport(value = "联系人1",required = true)
    @ExcelExport(value = "联系人1")
    private String contactOne;
    /**
     * 联系人1电话
     */
    @ExcelImport("联系人1电话")
    @ExcelExport(value = "联系人1电话")
    private String contactPhoneOne;
    /**
     * 联系人1手机
     */
    @ExcelImport(value = "联系人1手机",required = true)
    @ExcelExport(value = "联系人1手机")
    private String contactMobileOne;
    /**
     * 联系人1邮箱
     */
    @ExcelImport(value = "联系人1邮箱",required = true)
    @ExcelExport(value = "联系人1邮箱")
    private String contactEmailOne;
    /**
     *联系人1邮寄地址
     */
    @ExcelImport(value = "联系人1邮寄地址",required = true)
    @ExcelExport(value = "联系人1邮寄地址")
    private String contactMailingDdressOne;
    /**
     * 联系人1备注
     */
    @ExcelImport("联系人1备注")
    @ExcelExport(value = "联系人1备注")
    private String contactNoteOne;
    /**
     * 联系人2
     */
    @ExcelImport("联系人2")
    @ExcelExport(value = "联系人2")
    private String contactTwo;
    /**
     * 联系人2电话
     */
    @ExcelImport("联系人2电话")
    @ExcelExport(value = "联系人2电话")
    private String contactPhoneTwo;
    /**
     * 联系人2手机
     */
    @ExcelImport("联系人2手机")
    @ExcelExport(value = "联系人2手机")
    private String contactMobileTwo;
    /**
     * 联系人2邮箱
     */
    @ExcelImport("联系人2邮箱")
    @ExcelExport(value = "联系人2邮箱")
    private String contactEmailTwo;
    /**
     *联系人2邮寄地址
     */
    @ExcelImport("联系人2邮寄地址")
    @ExcelExport(value = "联系人2邮寄地址")
    private String contactMailingDdressTwo;
    /**
     * 联系人2备注
     */
    @ExcelImport("联系人2备注")
    @ExcelExport(value = "联系人2备注")
    private String contactNoteTwo;
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
