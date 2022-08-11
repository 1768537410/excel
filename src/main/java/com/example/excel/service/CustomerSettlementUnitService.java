package com.example.excel.service;


import com.example.excel.entity.CustomerSettlementUnit;
import com.example.excel.mapper.CustomerSettlementUnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * C03
 * @author zjc
 */
@Service
public class CustomerSettlementUnitService {

    @Autowired
    private CustomerSettlementUnitMapper customerSettlementUnitMapper;

    /**
     * 添加C03
     * @param customerSettlementUnit
     * @return
     */
    public int insertCustomerSettlementUnit(CustomerSettlementUnit customerSettlementUnit){
        int rows = customerSettlementUnitMapper.insertCustomerSettlementUnit(customerSettlementUnit);
        return rows;
    }
}
