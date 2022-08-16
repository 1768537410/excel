package com.example.excel.mapper;

import com.example.excel.entity.BasiInformation;
import com.example.excel.entity.ServiceAgreementItems;
import org.apache.ibatis.annotations.Mapper;

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


}
