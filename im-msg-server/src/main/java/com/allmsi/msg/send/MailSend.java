package com.allmsi.msg.send;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.allmsi.msg.config.Constant;
import com.allmsi.msg.log.dao.TransmissionMsgToMapper;
import com.allmsi.msg.model.po.ScheduleMessagePO;
import com.allmsi.msg.model.po.TransmissionMsgToPO;
import com.allmsi.msg.service.UserService;
import com.allmsi.sys.util.StrUtil;

@Service(Constant.SEND_MAIL)
public class MailSend implements BaseSend {

	@Value("${spring.mail.username}")
	private String mailUsername;

	@Autowired
	private JavaMailSender jms;

	@Resource
	private UserService userService;

	@Resource
	private TransmissionMsgToMapper transmissionMsgToDao;

	public static MailSend mailSend;

	@Override
	public void sendMessage(ScheduleMessagePO mailMsg) {
		if (StrUtil.notEmpty(mailMsg.getId())) {
			SimpleMailMessage mailMessage = send(mailMsg);
			TransmissionMsgToPO transMsgTo = new TransmissionMsgToPO();
			transMsgTo.setTransId(mailMsg.getId());
			transMsgTo.setMsgType(Constant.SEND_MAIL);
			try {
				transMsgTo.setFlag(99);
				transMsgTo.setMsgTo(mailMsg.getMsgTo());
				transmissionMsgToDao.updateByTrainIdAndType(transMsgTo);
			} catch (Exception e) {
				System.out.println("异常数据:" + mailMessage.toString());
				transMsgTo.setMsgTo(mailMsg.getMsgTo());
				transmissionMsgToDao.updateFlagWhenFailed(transMsgTo);
				e.printStackTrace();
			}
		}

	}

	/**
	 * 发送邮件
	 * 
	 * @param contentText
	 * @param user
	 * 
	 * @param user        发件人邮箱
	 * @param password    授权码（注意不是邮箱登录密码）
	 * @param host
	 * @param from        发件人
	 * @param to          接收者邮箱
	 * @param subject     邮件主题
	 * @param content     邮件内容
	 * @return success 发送成功 failure 发送失败
	 * @throws Exception
	 */
	private SimpleMailMessage send(ScheduleMessagePO mailMsg) {
		// 建立邮件消息
		SimpleMailMessage mainMessage = new SimpleMailMessage();
		// 发送者
		mainMessage.setFrom(mailUsername);

		// 接收者
		mainMessage.setTo(mailMsg.getMsgTo());
		mainMessage.setSentDate(new Date());
		// 发送的标题
		mainMessage.setSubject(mailMsg.getSubject());
		// 发送的内容
		mainMessage.setText(mailMsg.getContentText());
		jms.send(mainMessage);
		return mainMessage;
	}
}
