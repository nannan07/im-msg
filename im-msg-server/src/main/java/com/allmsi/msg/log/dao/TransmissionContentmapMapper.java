package com.allmsi.msg.log.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.allmsi.msg.model.po.TransmissionContentmapPO;

@Mapper
public interface TransmissionContentmapMapper {

	int insertBatch(List<TransmissionContentmapPO> list);
}