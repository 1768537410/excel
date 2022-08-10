package com.example.excel.service;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.CustomerInformation;
import com.example.excel.mapper.CustomerInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * C02
 */
@Service
public class CustomerInformationService {

    @Autowired
    private CustomerInformationMapper customerInformationMapper;

    /**
     * 添加C02
     * @param customerInformation
     * @return
     */
    public int insertCustomerInformation(CustomerInformation customerInformation){
        int rows = customerInformationMapper.insertCustomerInformation(customerInformation);
        return rows;
    }
}
