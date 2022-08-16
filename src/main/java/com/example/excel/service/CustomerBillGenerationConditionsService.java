package com.example.excel.service;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.CustomerBillGenerationConditions;
import com.example.excel.mapper.CustomerBillGenerationConditionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * C02 2表  客户账单生成条件
 * @author user
 */
@Service
public class CustomerBillGenerationConditionsService {

    @Autowired
    private CustomerBillGenerationConditionsMapper customerBillGenerationConditionsMapper;

    /**
     * 添加C02 2表
     * @param customerBillGenerationConditions
     * @return
     */
    public int insertCustomerBillGenerationConditions(CustomerBillGenerationConditions customerBillGenerationConditions){
        int rows = customerBillGenerationConditionsMapper.insertCustomerBillGenerationConditions(customerBillGenerationConditions);
        return rows;
    }

    /**
     * 根据账单生成条件名称查询C02 2表 中的客户简称
     * @param billGenerationConditionName
     * @return
     */
    public CustomerBillGenerationConditions findCustomerAbbreviationByBillGenerationConditionName(String billGenerationConditionName){

        return customerBillGenerationConditionsMapper.selectCustomerAbbreviationByBillGenerationConditionName(billGenerationConditionName);
    }



}
