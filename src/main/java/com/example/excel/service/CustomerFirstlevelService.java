package com.example.excel.service;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.CustomerFirstlevel;
import com.example.excel.mapper.CustomerFirstlevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * C08
 * @author user
 */
@Service
public class CustomerFirstlevelService {
    @Autowired
    private CustomerFirstlevelMapper customerFirstlevelMapper;

    /**
     * 添加Co1
     * @param customerFirstlevel
     * @return
     */
    public int insertCustomerFirstlevel(CustomerFirstlevel customerFirstlevel){
        int rows = customerFirstlevelMapper.insertCustomerFirstlevel(customerFirstlevel);
        return rows;
    }

    /**
     * C07 和同名 查询 C08 协议名
     * @param contractTitle
     * @return
     */
    public List<String> selectserviceAgreementNameBycontractTitle(String contractTitle){
        return customerFirstlevelMapper.selectserviceAgreementNameBycontractTitle(contractTitle);
    }




}
