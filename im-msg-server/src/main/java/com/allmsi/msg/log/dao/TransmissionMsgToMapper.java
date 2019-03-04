package com.allmsi.msg.log.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.allmsi.msg.model.po.TransmissionMsgToPO;

@Mapper
public interface TransmissionMsgToMapper {

	int updateByTrainIdAndType(TransmissionMsgToPO record);

	int updateFlagWhenFailed(TransmissionMsgToPO record);

	int insertBatch(List<TransmissionMsgToPO> transMsgToList);
}