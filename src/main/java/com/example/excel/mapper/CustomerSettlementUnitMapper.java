package com.example.excel.mapper;

import com.example.excel.entity.CustomerInformation;
import com.example.excel.entity.CustomerSettlementUnit;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zjc
 * C03 Mapper
 */
@Mapper
public interface CustomerSettlementUnitMapper {

    /**
     * 插入C03
     * @param customerSettlementUnit
     * @return
     */
    int insertCustomerSettlementUnit(CustomerSettlementUnit customerSettlementUnit);
}
