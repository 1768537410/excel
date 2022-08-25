package com.example.excel.mapper;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.PayrollInitialization;
import org.apache.ibatis.annotations.Mapper;

/**
 * C016
 * @author user
 */
@Mapper
public interface PayrollInitializationMapper {

    /**
     * 添加C01
     * @param payrollInitialization
     * @return
     */
    int insertPayrollInitialization(PayrollInitialization payrollInitialization);

}
