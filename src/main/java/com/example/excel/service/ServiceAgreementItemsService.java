package com.example.excel.service;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.ServiceAgreementItems;
import com.example.excel.mapper.ServiceAgreementItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
