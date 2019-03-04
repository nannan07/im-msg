package com.allmsi.msg.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.allmsi.msg.log.dao.ScheduleMessageMapper;
import com.allmsi.msg.log.dao.TransmissionMsgToMapper;
import com.allmsi.msg.model.po.ScheduleMessagePO;
import com.allmsi.msg.model.po.TransmissionMsgToPO;
import com.allmsi.msg.service.MessageSendService;

@Component
public class ExcpMessageScheduleTask {

	public final static long Time = 60 * 30000;//60 * 30000

	@Autowired
	private ScheduleMessageMapper taskMessageDao;

	@Autowired
	private MessageSendService messageSendService;

	@Autowired
	private TransmissionMsgToMapper transmissionMsgToDao;

	@Scheduled(fixedRate = Time)
	public void timedTask() {
		System.out.println("开始定时任务的执行!");
		// 警告状态边界值: 发送成功 99; 警告状态 98;
		String warnFlag = "5";
		// 发送警告邮件;
		List<ScheduleMessagePO> warnMesList = taskMessageDao.selectForTimedTask(warnFlag);
		if (warnMesList.size() != 0) {
			for (ScheduleMessagePO scheduleMessage : warnMesList) {
				sendWarnMail(scheduleMessage);
			}
		}
		warnFlag = null;
		// 检查24小时之内异常消息并重新发送
		List<ScheduleMessagePO> msgList = taskMessageDao.selectForTimedTask(warnFlag);
		if (msgList.size() != 0) {
			for (ScheduleMessagePO scheduleMessage : msgList) {
				scheduleMessage.setId(scheduleMessage.getId());
				scheduleMessage.setMsgFrom(scheduleMessage.getMsgFrom());
				scheduleMessage.setSubject(scheduleMessage.getSubject());
				scheduleMessage.setMsgTo(scheduleMessage.getMsgTo());
				scheduleMessage.setMsgType(scheduleMessage.getMsgType());
				scheduleMessage.setSysFlag(scheduleMessage.getSysFlag());
				messageSendService.messageSend(scheduleMessage);
			}
		}

	}

	private void sendWarnMail(ScheduleMessagePO scheduleMessage) {
		TransmissionMsgToPO transmissionMsgTo = new TransmissionMsgToPO();
		transmissionMsgTo.setMsgTo(scheduleMessage.getMsgTo());
		transmissionMsgTo.setMsgType(scheduleMessage.getMsgType());
		transmissionMsgTo.setTransId(scheduleMessage.getId());
		transmissionMsgTo.setFlag(98);// 该条消息已警告状态
		transmissionMsgToDao.updateByTrainIdAndType(transmissionMsgTo);
	}

}
