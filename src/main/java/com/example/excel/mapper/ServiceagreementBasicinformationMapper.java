package com.example.excel.mapper;


import com.example.excel.entity.ServiceagreementBasicinformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *C05
 *
 * @author zjc
 */
@Mapper
public interface ServiceagreementBasicinformationMapper {

    /**
     * 插入C05
     * @param serviceagreementBasicinformation
     * @return
     */
    int insertServiceagreementBasicinformation(ServiceagreementBasicinformation serviceagreementBasicinformation);

    /**
     * 查询服务协议名称
     * @return
     */
    List<String> SelectserviceAgreementName();

    /**
     * 根据 按整体费用和比例收取服务费 的是否 来查询 服务协议名称
     * @param chargeServiceFee
     * @return
     */
    List<String> SelectServiceAgreementNameByChargeServiceFee(String chargeServiceFee);

}
