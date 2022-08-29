package com.example.excel.service;

import com.example.excel.entity.BasiInformation;
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

    /**
     * 根据子客户简称查询C04中的客户简称
     * @param customerAbbreviation
     * @return
     */
    public SubCustomerInformation findSubCustomerInformationByCustomerAbbreviation(String customerAbbreviation){

        return subCustomerInformationMapper.selectSubCustomerInformationBycustomerAbbreviation(customerAbbreviation);
    }

    /**
     * 用子客户编号查询C04中的客户
     * @param customerNumber
     * @return
     */
    public SubCustomerInformation findSubCustomerInformationByCustomerNumber(String customerNumber){

        return subCustomerInformationMapper.selectSubCustomerInformationBycustomerNumber(customerNumber);
    }

    /**
     * 删除SubCustomerInformation所有数据
     */
    public void deleteSubCustomerInformation(){
        subCustomerInformationMapper.deleteSubCustomerInformation();
    }

}
