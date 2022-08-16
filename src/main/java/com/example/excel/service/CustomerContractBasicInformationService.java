package com.example.excel.service;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.CustomerContractBasicInformation;
import com.example.excel.mapper.CustomerContractBasicInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
