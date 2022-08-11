package com.example.excel.service;

import com.example.excel.entity.CustomerSettlementUnit;
import com.example.excel.entity.SubCustomerInformation;
import com.example.excel.mapper.SubCustomerInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * C04
 */
@Service
public class SubCustomerInformationService {

    @Autowired
    private SubCustomerInformationMapper subCustomerInformationMapper;

    /**
     * 插入C04
     * @param subCustomerInformation
     * @return
     */
    public int insertSubCustomerInformation(SubCustomerInformation subCustomerInformation){
        int rows = subCustomerInformationMapper.insertSubCustomerInformation(subCustomerInformation);
        return rows;
    }
}
