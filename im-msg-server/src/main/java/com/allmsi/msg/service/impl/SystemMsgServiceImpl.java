package com.allmsi.msg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.msg.mes.dao.SystemMsgMapper;
import com.allmsi.msg.model.po.SystemMsgPO;
import com.allmsi.msg.service.SystemMsgService;

@Service
public class SystemMsgServiceImpl implements SystemMsgService {
	
	@Resource
	private SystemMsgMapper systemMsgDao;

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(SystemMsgPO record) {
		
		return systemMsgDao.insertSelective(record);
	}

	@Override
	public SystemMsgPO selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(SystemMsgPO record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
