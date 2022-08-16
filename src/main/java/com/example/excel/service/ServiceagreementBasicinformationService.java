package com.example.excel.service;

import com.example.excel.entity.ServiceagreementBasicinformation;
import com.example.excel.entity.SubCustomerInformation;
import com.example.excel.mapper.ServiceagreementBasicinformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 查询服务协议名称
     * @return
     */
    public List<String> SelectserviceAgreementName(){
        return serviceagreementBasicinformationMapper.SelectserviceAgreementName();
    }

    /**
     * 根据 按整体费用和比例收取服务费 的是否 来查询 服务协议名
     * @param chargeServiceFee
     * @return
     */
    public List<String> SelectServiceAgreementNameByChargeServiceFee(String chargeServiceFee){
        return serviceagreementBasicinformationMapper.SelectServiceAgreementNameByChargeServiceFee(chargeServiceFee);
    }
}
