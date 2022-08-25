package com.example.excel.mapper;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.ServiceAgreementItems;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * C06
 *
 * @author user
 */
@Mapper
public interface ServiceAgreementItemsMapper {

    /**
     * 添加C06
     * @param serviceAgreementItems
     * @return
     */
    int insertServiceAgreementItems(ServiceAgreementItems serviceAgreementItems);

    /**
     * 根据 C08 协议名 查询C06 收费方式
     * @param serviceAgreementName
     * @return
     */
    List<String> selectchargingMethodByserviceAgreementName(String serviceAgreementName);

    /**
     * 根据服务项目查询协议名
     * @param serviceItems
     * @return
     */
     List<String> selectServiceAgreementNameByServiceItems(String serviceItems);



}
