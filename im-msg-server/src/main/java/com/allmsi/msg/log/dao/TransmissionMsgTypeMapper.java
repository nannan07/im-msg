package com.allmsi.msg.log.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.allmsi.msg.model.po.TransmissionMsgTypePO;

@Mapper
public interface TransmissionMsgTypeMapper {

	TransmissionMsgTypePO selectByTransIdAndMsgType(@Param("msgType") String msgType, @Param("transId") String transId);

	int insertBatch(List<TransmissionMsgTypePO> list);
}