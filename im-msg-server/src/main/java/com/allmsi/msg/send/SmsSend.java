package com.allmsi.msg.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allmsi.msg.config.Constant;
import com.allmsi.msg.log.dao.TransmissionMsgTypeMapper;
import com.allmsi.msg.model.po.ScheduleMessagePO;
import com.allmsi.msg.model.po.TransmissionMsgTypePO;

@Service(Constant.SEND_SMS)
public class SmsSend implements BaseSend {
	@Autowired
	private TransmissionMsgTypeMapper transmissionMsgTypeDao;

	@Override
	public void sendMessage(ScheduleMessagePO fusion) {
		TransmissionMsgTypePO transmissionMsgType = transmissionMsgTypeDao.selectByTransIdAndMsgType(Constant.SEND_SMS,
				fusion.getId());
	}
}
