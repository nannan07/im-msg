package com.allmsi.msg.log.dao;

import com.allmsi.msg.model.po.DBLogPO;

public interface DBLogMapper {

	int insertSelective(DBLogPO record);

}