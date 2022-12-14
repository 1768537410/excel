package com.example.excel.mapper;

import com.example.excel.entity.BasiInformation;
import org.apache.ibatis.annotations.Mapper;

/**
 * C01 Mapper
 * @author user
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
     * 用客户简称查询C01中的客户
     * @param customerAbbreviation
     * @return
     */
    BasiInformation selectBasiInformationBycustomerAbbreviation(String customerAbbreviation);

    /**
     * 用客户编号查询C01中的客户
     * @param customerNumber
     * @return
     */
    BasiInformation selectBasiInformationBycustomerNumber(String customerNumber);

    /**
     * 删除BasiInformation表中的所有数据
     * @return
     */
    int deleteBasiInformation();

}
