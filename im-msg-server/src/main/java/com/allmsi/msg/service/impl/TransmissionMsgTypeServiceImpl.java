package com.allmsi.msg.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.msg.log.dao.TemplateMapper;
import com.allmsi.msg.log.dao.TransmissionMsgTypeMapper;
import com.allmsi.msg.model.Message;
import com.allmsi.msg.model.po.TemplatePO;
import com.allmsi.msg.model.po.TransmissionMsgTypePO;
import com.allmsi.msg.service.TransmissionMsgTypeService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class TransmissionMsgTypeServiceImpl implements TransmissionMsgTypeService {

	@Resource
	private TransmissionMsgTypeMapper transmissionMsgTypeDao;

	@Resource
	private TemplateMapper templateDao;

	@Override
	public void save(Message message) {
		List<TransmissionMsgTypePO> transMsgTypeList = new ArrayList<>();
		String[] msgTypeArray = message.getMsgType();
		String[] tempCodeArray = message.getTemplateCode();
		if (tempCodeArray != null && tempCodeArray.length > 0 && msgTypeArray != null && msgTypeArray.length > 0) {
			String tempCode = "";
			TemplatePO template = new TemplatePO();
			if (msgTypeArray.length == tempCodeArray.length) {
				for (int i = 0; i < msgTypeArray.length; i++) {
					TransmissionMsgTypePO transmissionMsgType = new TransmissionMsgTypePO();
					String msgType = msgTypeArray[i];
					tempCode = tempCodeArray[i];
					if (StrUtil.notEmpty(msgType)) {
						if (StrUtil.isEmpty(tempCode)) {
							template = templateDao.selectByConfig(message.getSysFlag(), message.getTemplateType(),
									msgType);
							if (template != null) {
								tempCode = template.getCode();
							}
						}
						transmissionMsgType.setId(UUIDUtil.getUUID());
						transmissionMsgType.setTransId(message.getId());
						transmissionMsgType.setMsgType(msgType);
						transmissionMsgType.setTemplateCode(tempCode);
						transMsgTypeList.add(transmissionMsgType);
					}
				}
			} else {
				for (int i = 0; i < msgTypeArray.length; i++) {
					TransmissionMsgTypePO transmissionMsgType = new TransmissionMsgTypePO();
					String msgType = msgTypeArray[i];
					if (StrUtil.notEmpty(msgType)) {
						template = templateDao.selectByConfig(message.getSysFlag(), message.getTemplateType(), msgType);
						if (template != null) {
							tempCode = template.getCode();
						}
						transmissionMsgType.setId(UUIDUtil.getUUID());
						transmissionMsgType.setTransId(message.getId());
						transmissionMsgType.setMsgType(msgType);
						transmissionMsgType.setTemplateCode(tempCode);
						transMsgTypeList.add(transmissionMsgType);
					}
				}
			}
		}
		if (transMsgTypeList != null && transMsgTypeList.size() > 0) {
			transmissionMsgTypeDao.insertBatch(transMsgTypeList);
		}
	}
}
