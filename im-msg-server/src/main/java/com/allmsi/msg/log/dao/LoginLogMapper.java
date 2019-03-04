package com.allmsi.msg.log.dao;

import com.allmsi.msg.model.po.LoginLogPO;

public interface LoginLogMapper {

	int insertSelective(LoginLogPO loginLogPo);

}