package com.allmsi.msg.log.dao;

import org.apache.ibatis.annotations.Mapper;

import com.allmsi.msg.model.po.TransmissionPO;

@Mapper
public interface TransmissionMapper {

    int insertSelective(TransmissionPO record);

}