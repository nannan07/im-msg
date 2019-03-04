package com.allmsi.msg.send;

import com.allmsi.msg.model.po.ScheduleMessagePO;

public interface BaseSend {

	void sendMessage(ScheduleMessagePO scheduleMessage);

}
