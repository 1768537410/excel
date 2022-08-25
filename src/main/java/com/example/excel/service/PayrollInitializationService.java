package com.example.excel.service;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.PayrollInitialization;
import com.example.excel.mapper.PayrollInitializationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * C016
 * @author user
 */
@Service
public class PayrollInitializationService {

    @Autowired
    private PayrollInitializationMapper payrollInitializationMapper;

    /**
     * 添加Co16
     * @param payrollInitialization
     * @return
     */
    public int insertPayrollInitialization(PayrollInitialization payrollInitialization){
        int rows = payrollInitializationMapper.insertPayrollInitialization(payrollInitialization);
        return rows;
    }
}
