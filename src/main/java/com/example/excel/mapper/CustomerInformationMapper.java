package com.example.excel.mapper;

import com.example.excel.entity.CustomerInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * C02 1表 客户成本往来设定表 Mapper
 */
@Mapper
public interface CustomerInformationMapper {
    /**
     * 插入C02
     * @param customerInformation
     * @return
     */
    int insertCustomerInformation(CustomerInformation customerInformation);

    /**
     * 删除CustomerInformation数据
     * @return
     */
    int deleteCustomerInformation();




}
