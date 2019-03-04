package com.allmsi.msg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.msg.log.dao.InitialDataMapper;
import com.allmsi.msg.model.po.InitialDataPO;
import com.allmsi.msg.service.InitialDataService;
import com.allmsi.msg.util.Utils;

@Service
public class InitialDataServiceImpl implements InitialDataService {

	@Resource
	private InitialDataMapper dataDao;

	@Override
	public int insertSelective(String msg) {
		InitialDataPO data = new InitialDataPO();
		data.setId(Utils.getUUID());
		data.setStr(msg);
		return dataDao.insertSelective(data);
	}

}
