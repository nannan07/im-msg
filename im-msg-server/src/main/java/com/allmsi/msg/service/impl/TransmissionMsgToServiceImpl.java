package com.allmsi.msg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.msg.config.Constant;
import com.allmsi.msg.log.dao.TemplateMapper;
import com.allmsi.msg.log.dao.TransmissionMsgToMapper;
import com.allmsi.msg.model.Message;
import com.allmsi.msg.model.po.TemplatePO;
import com.allmsi.msg.model.po.TransmissionMsgToPO;
import com.allmsi.msg.model.vo.UserVO;
import com.allmsi.msg.service.TransmissionMsgToService;
import com.allmsi.msg.service.TransmissionMsgTypeService;
import com.allmsi.msg.service.UserService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class TransmissionMsgToServiceImpl implements TransmissionMsgToService {

	@Resource
	private TransmissionMsgTypeService transmissionMsgTypeService;

	@Resource
	private TransmissionMsgToMapper transmissionMsgToDao;

	@Resource
	private TemplateMapper templateDao;

	@Resource
	private UserService userService;

	@Override
	public List<TransmissionMsgToPO> save(Message message) {
		List<TransmissionMsgToPO> transMsgToList = new ArrayList<>();
		Map<String, String> contentMap = new HashMap<>();
		contentMap = message.getContentmap();
		String objId = message.getObjId();
		String contentText = message.getContentText();
		String[] msgTypeArray = message.getMsgType();
		String[] msgToArray = message.getMsgTo();
		String[] tempCodeArray = message.getTemplateCode();
		String[] msgUrlArray = getMsgUrl(message.getSysFlag(), message.getTemplateType(), msgTypeArray,
				message.getMsgTypeUrl());
		if (msgTypeArray != null && msgTypeArray.length > 0) {
			TemplatePO template = new TemplatePO();
			if (StrUtil.isEmpty(contentText)) {
				if (msgTypeArray.length == tempCodeArray.length) {
					for (int i = 0; i < msgTypeArray.length; i++) {
						String text = "";
						String msgType = msgTypeArray[i];
						String msgUrl = msgUrlArray[i];
						String tempCode = tempCodeArray[i];
						if (StrUtil.notEmpty(msgType)) {
							if (StrUtil.isEmpty(tempCode)) {
								template = templateDao.selectByConfig(message.getSysFlag(), message.getTemplateType(),
										msgType);
							} else {
								template = templateDao.selectByCode(tempCode);
							}
							if (template != null) {
								text = new String(template.getText());
								transMsgToList.addAll(getTransMsgToByContentMap(contentMap, msgToArray, msgType, msgUrl,
										text, message.getId(), objId));
							}
						}
					}
				} else {
					for (int i = 0; i < msgTypeArray.length; i++) {
						String text = "";
						String msgType = msgTypeArray[i];
						String msgUrl = msgUrlArray[i];
						if (StrUtil.notEmpty(msgType)) {
							template = templateDao.selectByConfig(message.getSysFlag(), message.getTemplateType(),
									msgType);
							if (template != null) {
								text = new String(template.getText());
							}
							transMsgToList.addAll(getTransMsgToByContentMap(contentMap, msgToArray, msgType, msgUrl,
									text, message.getId(), objId));
						}
					}
				}
			} else {
				transMsgToList.addAll(getTransMsgToByContentText(msgToArray, tempCodeArray, msgTypeArray, msgUrlArray,
						contentText, message.getId(), objId));
			}
		}
		if (transMsgToList != null && transMsgToList.size() > 0) {
			transmissionMsgToDao.insertBatch(transMsgToList);
		}
		return transMsgToList;
	}

	private List<TransmissionMsgToPO> getTransMsgToByContentText(String[] msgToArray, String[] tempCodeArray,
			String[] msgTypeArray, String[] msgUrlArray, String contentText, String transId, String objId) {
		List<TransmissionMsgToPO> transMsgToList = new ArrayList<>();
		if (msgToArray != null && msgToArray.length > 0) {
			for (String msgTo : msgToArray) {
				TransmissionMsgToPO transmissionMsgTo = new TransmissionMsgToPO();
				UserVO user = userService.selectUserById(msgTo);
				transmissionMsgTo.setTransId(transId);
				transmissionMsgTo.setObjId(objId);
				transmissionMsgTo.setContentText(contentText);
				for (int i = 0; i < msgTypeArray.length; i++) {
					transmissionMsgTo.setId(UUIDUtil.getUUID());
					String msgType = msgTypeArray[i];
					String msgUrl = msgUrlArray[i];
					if (StrUtil.notEmpty(msgType)) {
						if (Constant.SEND_MAIL.equals(msgType)) {// 邮件
							if (user != null) {
								transmissionMsgTo.setMsgTo(user.getEmail());
							}
						} else if (Constant.SEND_SMS.equals(msgType)) {// 手机号
							if (user != null) {
								transmissionMsgTo.setMsgTo(user.getPhone());
							}
						} else {
							transmissionMsgTo.setMsgTo(msgTo);
						}
						transmissionMsgTo.setMsgType(msgType);
						transmissionMsgTo.setUrl(msgUrl);
					}
				}
				transMsgToList.add(transmissionMsgTo);
			}
		}
		return transMsgToList;
	}

	private List<TransmissionMsgToPO> getTransMsgToByContentMap(Map<String, String> contentMap, String[] msgToArray,
			String msgType, String msgUrl, String text, String transId, String objId) {
		List<TransmissionMsgToPO> transMsgToList = new ArrayList<>();
		if (msgToArray != null && msgToArray.length > 0) {
			for (String msgTo : msgToArray) {
				TransmissionMsgToPO transmissionMsgTo = new TransmissionMsgToPO();
				String contentText = replaceText(contentMap, text);
				transmissionMsgTo.setId(UUIDUtil.getUUID());
				transmissionMsgTo.setTransId(transId);
				transmissionMsgTo.setObjId(objId);
				transmissionMsgTo.setMsgType(msgType);
				transmissionMsgTo.setContentText(contentText);
				transmissionMsgTo.setUrl(msgUrl);
				UserVO user = userService.selectUserById(msgTo);
				if (contentMap != null) {
					contentMap.put("$(toName)", user.getUserName());
				}
				if (Constant.SEND_MAIL.equals(msgType)) {// 邮件
					if (user != null) {
						transmissionMsgTo.setMsgTo(user.getEmail());
					}
				} else if (Constant.SEND_SMS.equals(msgType)) {// 手机号
					if (user != null) {
						transmissionMsgTo.setMsgTo(user.getPhone());
					}
				} else {
					transmissionMsgTo.setMsgTo(msgTo);
				}
				transMsgToList.add(transmissionMsgTo);
			}
		}
		return transMsgToList;
	}

	private String[] getMsgUrl(String sysFlag, String templateType, String[] msgTypeArray, String[] msgUrlArray) {
		String msgType = "";
		if (msgTypeArray != null && msgTypeArray.length > 0) {
			String[] array = new String[msgTypeArray.length];
			if (msgTypeArray.length == msgUrlArray.length) {
				for (int i = 0; i < msgTypeArray.length; i++) {
					msgType = msgTypeArray[i];
					if (StrUtil.notEmpty(msgType)) {
						array[i] = msgUrlArray[i];
					}
				}
				msgUrlArray = array;
			} else {
				for (int i = 0; i < msgTypeArray.length; i++) {
					msgType = msgTypeArray[i];
					if (StrUtil.notEmpty(msgType)) {
						array[i] = "";
					}
				}
				msgUrlArray = array;
			}
		}
		return msgUrlArray;
	}

	private String replaceText(Map<String, String> contentMap, String text) {
		if (contentMap != null && contentMap.size() > 0 && StrUtil.notEmpty(text)) {
			for (String key : contentMap.keySet()) {
				if (text.indexOf(key) != -1) {
					text = text.replace(key, contentMap.get(key));
				}
			}
		}
		return text;
	}

}
