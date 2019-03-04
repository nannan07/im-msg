package com.allmsi.msg.mes.dao;

import com.allmsi.msg.model.po.UserPO;

public interface UserMapper {

	UserPO selectByPrimaryKey(String id);
}