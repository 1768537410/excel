package com.example.excel.mapper;

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
}
