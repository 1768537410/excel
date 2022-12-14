package com.example.excel.service;

import com.example.excel.entity.BasiInformation;
import com.example.excel.mapper.BasiInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * C01
 */
@Service
public class BasiInformationService {

    @Autowired
    private BasiInformationMapper basiInformationMapper;

    /**
     * 添加Co1
     * @param basiInformation
     * @return
     */
    public int insertBasiInformation(BasiInformation basiInformation){
        int rows = basiInformationMapper.insertBasiInformation(basiInformation);
        return rows;
    }

    /**
     * 根据客户简称查询C01中的客户简称
     * @param customerAbbreviation
     * @return
     */
    public BasiInformation findBasiInformationByCustomerAbbreviation(String customerAbbreviation){

        return basiInformationMapper.selectBasiInformationBycustomerAbbreviation(customerAbbreviation);
    }

    /**
     * 用客户编号查询C01中的客户
     * @param customerNumber
     * @return
     */
    public BasiInformation findBasiInformationByCustomerNumber(String customerNumber){

        return basiInformationMapper.selectBasiInformationBycustomerNumber(customerNumber);
    }

    /**
     * 删除C01数据库中的所有数据
     */
    public void deleteBasiInformation(){
        basiInformationMapper.deleteBasiInformation();
    }


}
