package com.allmsi.msg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.allmsi.msg.config.Constant;
import com.allmsi.msg.log.dao.LoginLogMapper;
import com.allmsi.msg.model.po.LoginLogPO;
import com.allmsi.msg.service.BaseLogService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service(Constant.LOG_LOGIN)
public class LoginLogServiceImpl implements BaseLogService {

	@Resource
	private LoginLogMapper loginLogDao;

	@Override
	public void saveLog(String msg) {
		if (StrUtil.notEmpty(msg)) {
			LoginLogPO loginLogPO = JSON.parseObject(msg, new TypeReference<LoginLogPO>() {
			});
			loginLogPO.setId(UUIDUtil.getUUID());
			loginLogDao.insertSelective(loginLogPO);
			System.out.println("MQ--------保存login;当前线程:" + Thread.currentThread().getName());
		}

	}

}
