package com.allmsi.msg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.allmsi.msg.config.Constant;
import com.allmsi.msg.model.po.ScheduleMessagePO;
import com.allmsi.msg.send.DingSend;
import com.allmsi.msg.send.MailSend;
import com.allmsi.msg.send.SmsSend;
import com.allmsi.msg.send.SystemMsgSend;
import com.allmsi.msg.service.MessageSendService;

@Service
public class MessageSendServiceImpl implements MessageSendService {

	@Autowired
	private SmsSend smsSend;

	@Autowired
	private DingSend dingSend;

	@Autowired
	private MailSend mailSend;

	@Autowired
	private SystemMsgSend systemMsgSend;

	@Async("asyncExecutor")
	@Override
	public void messageSend(ScheduleMessagePO scheduleMessage) {
		if (scheduleMessage != null) {
			// 按照类型相应处理
			String msgtype = scheduleMessage.getMsgType();
			switch (msgtype) {
			case Constant.SEND_DING:
				dingSend.sendMessage(scheduleMessage);
				break;
			case Constant.SEND_MAIL:
				mailSend.sendMessage(scheduleMessage);
				break;
			case Constant.SEND_SMS:
				smsSend.sendMessage(scheduleMessage);
				break;
			case Constant.SEND_SYSTEM_PC:
				systemMsgSend.sendMessage(scheduleMessage);
				break;
			case Constant.SEND_SYSTEM_DING:
				systemMsgSend.sendMessage(scheduleMessage);
				break;
			}

		}
	}
}
