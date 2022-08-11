package com.example.excel.service;

import com.example.excel.entity.ServiceagreementBasicinformation;
import com.example.excel.entity.SubCustomerInformation;
import com.example.excel.mapper.ServiceagreementBasicinformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * C05
 */
@Service
public class ServiceagreementBasicinformationService {
    @Autowired
    private ServiceagreementBasicinformationMapper serviceagreementBasicinformationMapper;

    /**
     * 插入C05
     * @param serviceagreementBasicinformation
     * @return
     */
    public int insertServiceagreementBasicinformation(ServiceagreementBasicinformation serviceagreementBasicinformation){
        int rows = serviceagreementBasicinformationMapper.insertServiceagreementBasicinformation(serviceagreementBasicinformation);
        return rows;
    }
}
