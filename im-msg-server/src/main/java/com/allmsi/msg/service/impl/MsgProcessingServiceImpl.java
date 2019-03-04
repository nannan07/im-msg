package com.allmsi.msg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.allmsi.msg.log.dao.TemplateMapper;
import com.allmsi.msg.model.Message;
import com.allmsi.msg.model.po.ScheduleMessagePO;
import com.allmsi.msg.model.po.TemplatePO;
import com.allmsi.msg.model.po.TransmissionMsgToPO;
import com.allmsi.msg.model.vo.LogMsgVO;
import com.allmsi.msg.service.InitialDataService;
import com.allmsi.msg.service.LogService;
import com.allmsi.msg.service.MessageSendService;
import com.allmsi.msg.service.MessageService;
import com.allmsi.msg.service.MsgProcessingService;
import com.allmsi.sys.config.SpringContextRegister;

@Service
public class MsgProcessingServiceImpl implements MsgProcessingService {

	@Resource
	private LogService logService;

	@Resource
	private TemplateMapper templateDao;

	@Resource
	private MessageService messageService;

	@Resource
	private InitialDataService dataService;

	@Resource
	private MessageSendService messageSendService;

	@Resource
	private SpringContextRegister springContextRegister;

	// 消息处理
	@SuppressWarnings("unchecked")
	@Override
	public Message messageProcessing(String msg) {
		msg = msg.replaceAll("\\\\", "");
		// 源数据落地
		dataService.insertSelective(msg);
		// 转换成实体
		Message msgBean = JSON.parseObject(msg, new TypeReference<Message>() {
		});
		// 实体解析,各项数据落地
		List<TransmissionMsgToPO> transMsgToList = (List<TransmissionMsgToPO>) messageService.save(msgBean);
		if (transMsgToList != null && transMsgToList.size() > 0) {
			TemplatePO template = new TemplatePO();
			
			String msgTypeUrl = "";
			for (TransmissionMsgToPO transMsgToPO : transMsgToList) {
				// 发送消息
				String text = "";
				ScheduleMessagePO scheduleMessage = new ScheduleMessagePO(transMsgToPO, msgBean);
				String sysFlag = msgBean.getSysFlag();
				String msgType = transMsgToPO.getMsgType();
				String msgUrl = transMsgToPO.getUrl();
				template = templateDao.getMsgUrlTemplate(sysFlag, msgType);
				if (template != null) {
					text = new String(template.getText());
					msgTypeUrl = text + msgUrl;
					scheduleMessage.setUrl(msgTypeUrl);
				}
				messageSendService.messageSend(scheduleMessage);
			}
		}
		return msgBean;
	}

	// 日志保存
	@Override
	public void logProcessing(String msg) {
		LogMsgVO log = JSON.parseObject(msg, new TypeReference<LogMsgVO>() {
		});
		if (log != null) {
			logService.saveLog(log);
		}
	}

}
