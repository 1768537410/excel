package com.example.excel.mapper;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.CustomerBillGenerationConditions;
import com.example.excel.entity.CustomerContractBasicInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * C02 2表  客户账单生成条件
 * @author user
 */
@Mapper
public interface CustomerBillGenerationConditionsMapper {
    /**
     * 添加C02 2表
     * @param customerBillGenerationConditions
     * @return
     */
    int insertCustomerBillGenerationConditions(CustomerBillGenerationConditions customerBillGenerationConditions);


    /**
     * 根据账单生成条件名称查询C02 2表 中的客户简称
     * @param billGenerationConditionName
     * @return
     */
    CustomerBillGenerationConditions selectCustomerAbbreviationByBillGenerationConditionName(String billGenerationConditionName);

}
