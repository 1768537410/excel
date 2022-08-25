package com.example.excel.service;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.ServiceAgreementItems;
import com.example.excel.mapper.ServiceAgreementItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * C06
 */
@Service
public class ServiceAgreementItemsService {

    @Autowired
    private ServiceAgreementItemsMapper serviceAgreementItemsMapper;
    /**
     * 添加Co6
     * @param serviceAgreementItems
     * @return
     */
    public int insertServiceAgreementItems(ServiceAgreementItems serviceAgreementItems){
        int rows = serviceAgreementItemsMapper.insertServiceAgreementItems(serviceAgreementItems);
        return rows;
    }

    /**
     * 根据 C08 协议名 查询C06 收费方式
     * @param serviceAgreementName
     * @return
     */
    public List<String> selectchargingMethodByserviceAgreementName(String serviceAgreementName){
        return serviceAgreementItemsMapper.selectchargingMethodByserviceAgreementName(serviceAgreementName);
    }

    /**
     * 根据服务项目查询协议名
     * @param serviceItems
     * @return
     */
    public List<String> selectServiceAgreementNameByServiceItems(String serviceItems){
        return serviceAgreementItemsMapper.selectServiceAgreementNameByServiceItems(serviceItems);
    }

}
