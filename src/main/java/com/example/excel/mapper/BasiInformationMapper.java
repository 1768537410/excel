package com.example.excel.mapper;

import com.example.excel.entity.BasiInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * C01 Mapper
 */
@Mapper
public interface BasiInformationMapper {
    /**
     * 添加C01
     * @param basiInformation
     * @return
     */
    int insertBasiInformation(BasiInformation basiInformation);

    /**
     * 用C02中的客户简称查询C01中的客户
     * @param customerAbbreviation
     * @return
     */
    BasiInformation selectBasiInformationBycustomerAbbreviation(String customerAbbreviation);

}
