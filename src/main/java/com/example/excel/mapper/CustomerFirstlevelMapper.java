package com.example.excel.mapper;

import com.example.excel.entity.CustomerContractBasicInformation;
import com.example.excel.entity.CustomerFirstlevel;
import org.apache.ibatis.annotations.Mapper;

/**
 * C08
 * @author user
 */
@Mapper
public interface CustomerFirstlevelMapper {
    /**
     * 添加C08
     * @param customerFirstlevel
     * @return
     */
    int insertCustomerFirstlevel(CustomerFirstlevel customerFirstlevel);
}
