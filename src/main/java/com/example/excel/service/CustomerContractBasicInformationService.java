package com.example.excel.service;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.CustomerContractBasicInformation;
import com.example.excel.mapper.CustomerContractBasicInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * C07
 * @author user
 */
@Service
public class CustomerContractBasicInformationService {


    @Autowired
    private CustomerContractBasicInformationMapper customerContractBasicInformationMapper;


    public int insertCustomerContractBasicInformation(CustomerContractBasicInformation customerContractBasicInformation){
        int rows = customerContractBasicInformationMapper.insertCustomerContractBasicInformation (customerContractBasicInformation);
        return rows;
    }

    /**
     * 根据合同协议级别查询合名称
     * @param contractLevel
     * @return
     */
    public List<String> selectCustomerContractBasicInformationBycontractLevel(String contractLevel){
        return customerContractBasicInformationMapper.selectCustomerContractBasicInformationBycontractLevel(contractLevel);
    }
}
