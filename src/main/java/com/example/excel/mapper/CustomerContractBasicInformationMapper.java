package com.example.excel.mapper;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.CustomerContractBasicInformation;
import org.apache.ibatis.annotations.Mapper;

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


}
