package com.allmsi.msg.send;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.allmsi.msg.log.dao.TransmissionMsgToMapper;
import com.allmsi.msg.mes.dao.SystemMsgMapper;
import com.allmsi.msg.model.po.ScheduleMessagePO;
import com.allmsi.msg.model.po.SystemMsgPO;
import com.allmsi.msg.model.po.TransmissionMsgToPO;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Component
public class SystemMsgSend implements BaseSend {

	@Resource
	private SystemMsgMapper systemMsgDao;

	@Resource
	private TransmissionMsgToMapper transmissionMsgToDao;

	@Override
	public void sendMessage(ScheduleMessagePO innerMsg) {
		if (StrUtil.notEmpty(innerMsg.getId())) {
			SystemMsgPO sm = new SystemMsgPO();
			sm.setId(UUIDUtil.getUUID());
			sm.setTransId(innerMsg.getTransId());
			sm.setObjId(innerMsg.getObjId());
			sm.setTemplateType(innerMsg.getTemplateType());
			sm.setMsgFrom(innerMsg.getMsgFrom());
			sm.setMsgType(innerMsg.getMsgType());
			sm.setSysFlag(innerMsg.getSysFlag());
			sm.setSubject(innerMsg.getSubject());
			sm.setContentText(innerMsg.getContentText());
			sm.setUrl(innerMsg.getUrl());
			sm.setMsgTo(innerMsg.getMsgTo());
			int num = systemMsgDao.insertSelective(sm);
			if (num > 0) {
				TransmissionMsgToPO transMsgTo = new TransmissionMsgToPO();
				transMsgTo.setFlag(99);
				transmissionMsgToDao.updateByTrainIdAndType(transMsgTo);
			}
		}

	}
}
