package com.allmsi.msg.service;

import com.allmsi.msg.model.po.ScheduleMessagePO;

public interface MessageSendService {

	void messageSend(ScheduleMessagePO scheduleMessage);
}
