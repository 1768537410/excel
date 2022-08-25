package com.example.excel.mapper;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.CustomerContractBasicInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * C07
 * @author user
 */
@Mapper
public interface CustomerContractBasicInformationMapper {

    /**
     * 添加C07
     * @param customerContractBasicInformation
     * @return
     */
    int insertCustomerContractBasicInformation(CustomerContractBasicInformation customerContractBasicInformation);

    /**
     * 根据合同协议级别查询合名称
     * @param contractLevel
     * @return
     */
    List<String> selectCustomerContractBasicInformationBycontractLevel(String contractLevel);



}
