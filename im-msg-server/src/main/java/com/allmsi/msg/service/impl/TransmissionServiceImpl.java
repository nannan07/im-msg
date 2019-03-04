package com.allmsi.msg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.msg.log.dao.TemplateMapper;
import com.allmsi.msg.log.dao.TransmissionMapper;
import com.allmsi.msg.model.Message;
import com.allmsi.msg.model.po.TransmissionPO;
import com.allmsi.msg.service.TransmissionService;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class TransmissionServiceImpl implements TransmissionService {

	@Resource
	private TransmissionMapper transmissionDao;
	@Resource
	private TemplateMapper templateDao;

	@Override
	public TransmissionPO save(Message message) {
		TransmissionPO transmission = new TransmissionPO(message);
		int msg = 0;
		if (message != null) {
			transmission.setId(UUIDUtil.getUUID());
			msg = transmissionDao.insertSelective(transmission);
		}
		return (msg == 0) ? null : transmission;
	}

}
