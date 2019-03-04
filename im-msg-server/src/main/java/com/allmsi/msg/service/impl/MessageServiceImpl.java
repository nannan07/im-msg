package com.allmsi.msg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.msg.model.Message;
import com.allmsi.msg.model.po.TransmissionMsgToPO;
import com.allmsi.msg.model.po.TransmissionPO;
import com.allmsi.msg.service.MessageService;
import com.allmsi.msg.service.TransmissionContentmapService;
import com.allmsi.msg.service.TransmissionMsgToService;
import com.allmsi.msg.service.TransmissionMsgTypeService;
import com.allmsi.msg.service.TransmissionService;
import com.allmsi.sys.util.StrUtil;

@Service
public class MessageServiceImpl implements MessageService {

	@Resource
	private TransmissionService transmissionService;

	@Resource
	private TransmissionMsgToService transmissionMsgToService;

	@Resource
	private TransmissionMsgTypeService transmissionMsgTypeService;

	@Resource
	private TransmissionContentmapService transmissionContentmapService;

	@Override
	public Object save(Message message) {
		if (StrUtil.isEmpty(message.getSysFlag()) || StrUtil.isEmpty(message.getTemplateType())
				|| StrUtil.isEmpty(message.getMsgFrom())) {
			return null;
		}
		TransmissionPO transmission = transmissionService.save(message);
		if (transmission != null) {
			message.setId(transmission.getId());
		}
		transmissionMsgTypeService.save(message);
		transmissionContentmapService.save(message);
		List<TransmissionMsgToPO> transMsgToList = transmissionMsgToService.save(message);
		return transMsgToList;
	}
}
