package com.allmsi.msg.log.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.allmsi.msg.model.po.ScheduleMessagePO;

@Mapper
public interface ScheduleMessageMapper {

	List<ScheduleMessagePO> selectForTimedTask(String warnFlag);

	List<ScheduleMessagePO> listMsgTo();

}