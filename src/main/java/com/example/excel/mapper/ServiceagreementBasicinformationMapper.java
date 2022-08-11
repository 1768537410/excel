package com.example.excel.mapper;


import com.example.excel.entity.ServiceagreementBasicinformation;
import org.apache.ibatis.annotations.Mapper;

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


}
