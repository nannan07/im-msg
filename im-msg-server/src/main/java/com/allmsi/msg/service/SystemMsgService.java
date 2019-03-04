package com.allmsi.msg.service;

import com.allmsi.msg.model.po.SystemMsgPO;

public interface SystemMsgService {
	int deleteByPrimaryKey(String id);

    int insertSelective(SystemMsgPO record);

    SystemMsgPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemMsgPO record);
}
