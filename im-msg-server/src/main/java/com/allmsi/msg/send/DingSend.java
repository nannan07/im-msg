package com.allmsi.msg.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.allmsi.msg.config.Constant;
import com.allmsi.msg.exception.OApiException;
import com.allmsi.msg.log.dao.TransmissionMsgToMapper;
import com.allmsi.msg.model.po.ScheduleMessagePO;
import com.allmsi.msg.model.po.TransmissionMsgToPO;
import com.allmsi.msg.util.HttpHelper;
import com.allmsi.sys.util.StrUtil;

@Service(Constant.SEND_DING)
public class DingSend implements BaseSend {
	@Autowired
	private TransmissionMsgToMapper transmissionMsgToDao;

	@Override
	public void sendMessage(ScheduleMessagePO dingMsg) {
		if (StrUtil.notEmpty(dingMsg.getId())) {
			String re = sendDingWorkMessage(dingMsg);
			TransmissionMsgToPO transMsgTo = new TransmissionMsgToPO();
			transMsgTo.setTransId(dingMsg.getId());
			transMsgTo.setMsgType(Constant.SEND_DING);
			if (("0").equals(re)) {
				// 发送成功
				transMsgTo.setFlag(99);
				transMsgTo.setMsgTo(dingMsg.getMsgTo());
				transmissionMsgToDao.updateByTrainIdAndType(transMsgTo);

			} else {
				// 发送失败flag+1
				transMsgTo.setMsgTo(dingMsg.getMsgTo());
				transmissionMsgToDao.updateFlagWhenFailed(transMsgTo);

			}
		}
	}

	private String sendDingWorkMessage(ScheduleMessagePO dingMsg) {
		String url = Constant.SEND_WorkMessage_Url.replace("ACCESS_TOKEN", getAccessToken());
		System.out.println("----------" + url);
		JSONObject param = new JSONObject();
		JSONObject oa = new JSONObject();
		JSONObject msg = new JSONObject();
		JSONObject head = new JSONObject();
		JSONObject body = new JSONObject();
		oa.put("message_url", dingMsg.getContentText());
		head.put("bgcolor", "FF4c9fe3");
		head.put("text", "content");
		oa.put("head", head);
		body.put("title", dingMsg.getSubject());
		oa.put("body", body);
		msg.put("msgtype", "oa");
		msg.put("oa", oa);
		param.put("msg", msg);
		param.put("agent_id", Constant.DING_AGENTID);
		param.put("userid_list", String.join(",", dingMsg.getMsgTo()));
		System.err.println(param.toString());
		JSONObject result = null;
		try {
			result = HttpHelper.httpPost(url, param);
		} catch (OApiException e) {
			e.printStackTrace();
		}
		System.out.println("+++++++++" + result.toString());
		result.get("task_id");
		return result.get("errcode").toString();
	}

	// 发送工作内容消息
	public String sendDingWorkMessage(TransmissionMsgToPO transmissionMsgTo, String content) {

		String url = Constant.SEND_WorkMessage_Url.replace("ACCESS_TOKEN", getAccessToken());
		System.out.println("----------" + url);
		JSONObject param = new JSONObject();
		JSONObject oa = new JSONObject();
		JSONObject msg = new JSONObject();
		JSONObject head = new JSONObject();
		JSONObject body = new JSONObject();
		oa.put("message_url", transmissionMsgTo.getContentText());
		head.put("bgcolor", "FF4c9fe3");
		head.put("text", "content");
		oa.put("head", head);
		body.put("title", content);
		oa.put("body", body);
		msg.put("msgtype", "oa");
		msg.put("oa", oa);
		param.put("msg", msg);
		param.put("agent_id", Constant.DING_AGENTID);
		param.put("userid_list", String.join(",", transmissionMsgTo.getMsgTo()));
		// System.out.println(param.toString());
		JSONObject result = null;
		try {
			result = HttpHelper.httpPost(url, param);
		} catch (OApiException e) {
			e.printStackTrace();
		}
		System.out.println("+++++++++" + result.toString());
		result.get("task_id");
		return result.get("errcode").toString();
	}

	// 获取token
	private static String getAccessToken() {
		String url = "https://oapi.dingtalk.com/gettoken?corpid=" + Constant.DING_CORPID + "&corpsecret="
				+ Constant.DING_CORPSECRET;
		// System.out.println(url);
		String token = null;
		try {
			JSONObject obj = HttpHelper.httpGet(url);
			token = obj.get("access_token").toString();
		} catch (OApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}

}
