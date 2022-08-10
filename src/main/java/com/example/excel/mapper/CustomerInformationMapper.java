package com.example.excel.mapper;

import com.example.excel.entity.CustomerInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * C02 Mapper
 */
@Mapper
public interface CustomerInformationMapper {
    /**
     * 插入C02
     * @param customerInformation
     * @return
     */
    int insertCustomerInformation(CustomerInformation customerInformation);




}
