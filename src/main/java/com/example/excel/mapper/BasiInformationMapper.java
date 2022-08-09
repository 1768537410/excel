package com.example.excel.mapper;

import com.example.excel.entity.BasiInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BasiInformationMapper {

    BasiInformation selectBasiInformation();

}
