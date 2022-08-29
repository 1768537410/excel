package com.example.excel.mapper;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.CustomerContractBasicInformation;
import com.example.excel.entity.CustomerFirstlevel;
import jdk.internal.dynalink.linker.LinkerServices;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * C07 和同名 查询 C08 协议名
     * @param contractTitle
     * @return
     */
    List<String> selectserviceAgreementNameBycontractTitle(String contractTitle);

    /**
     * 删除CustomerFirstlevel中的数据
     * @return
     */
    int deleteCustomerFirstlevel();

}
