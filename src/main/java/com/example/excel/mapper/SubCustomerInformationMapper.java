package com.example.excel.mapper;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.SubCustomerInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * C04
 * @author zjc
 */
@Mapper
public interface SubCustomerInformationMapper {
    /**
     * 插入C04
     * @param subCustomerInformation
     * @return
     */
    int insertSubCustomerInformation(SubCustomerInformation subCustomerInformation);

    /**
     * 用子客户简称查询C04中的客户
     * @param customerAbbreviation
     * @return
     */
    SubCustomerInformation selectSubCustomerInformationBycustomerAbbreviation(String customerAbbreviation);

    /**
     * 用子客户编号查询C04中的客户
     * @param customerNumber
     * @return
     */
    SubCustomerInformation selectSubCustomerInformationBycustomerNumber(String customerNumber);
}
