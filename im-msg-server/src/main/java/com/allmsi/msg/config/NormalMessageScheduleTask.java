package com.allmsi.msg.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.allmsi.msg.log.dao.ScheduleMessageMapper;
import com.allmsi.msg.model.po.ScheduleMessagePO;
import com.allmsi.msg.service.MessageSendService;

public class NormalMessageScheduleTask {

	public final static long Time = 6000;//60 * 30000

	@Autowired
	private MessageSendService messageSendService;

	@Autowired
	private ScheduleMessageMapper taskMessageMapper;

	@Scheduled(fixedRate = Time)
	public void messageTask() {
		List<ScheduleMessagePO> msgList = taskMessageMapper.listMsgTo();
		if (msgList != null && msgList.size() > 0) {
			for (ScheduleMessagePO scheduleMessage : msgList) {
				messageSendService.messageSend(scheduleMessage);
			}

		}

	}

}
