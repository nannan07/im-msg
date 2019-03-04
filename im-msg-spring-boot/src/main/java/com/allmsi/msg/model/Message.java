package com.allmsi.msg.model;

import java.util.Arrays;
import java.util.Map;

public class Message {

	private String id;

	private String msgFrom;// 发件人

	private String[] msgTo;// 收件人

	private String objId;

	private String subject;// 主题

	private String sysFlag;// 系统标识

	private String templateType;// 模版类型

	private String[] msgType;// 消息类型

	private String[] templateCode;// 模版编号

	private String contentText;// 信息（自定义）

	private String[] msgTypeUrl;// 消息链接

	private Map<String, String> contentmap;// 信息（模板）

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String[] getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String[] templateCode) {
		this.templateCode = templateCode;
	}

	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String[] getMsgType() {
		return msgType;
	}

	public void setMsgType(String[] msgType) {
		this.msgType = msgType;
	}

	public String[] getMsgTo() {
		return msgTo;
	}

	public void setMsgTo(String[] msgTo) {
		this.msgTo = msgTo;
	}

	public Map<String, String> getContentmap() {
		return contentmap;
	}

	public String[] getMsgTypeUrl() {
		return msgTypeUrl;
	}

	public void setMsgTypeUrl(String[] msgTypeUrl) {
		this.msgTypeUrl = msgTypeUrl;
	}

	public void setContentmap(Map<String, String> contentmap) {
		this.contentmap = contentmap;
	}

	public Message() {

	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", msgFrom=" + msgFrom + ", msgTo=" + Arrays.toString(msgTo) + ", subject="
				+ subject + ", sysFlag=" + sysFlag + ", templateType=" + templateType + ", msgType="
				+ Arrays.toString(msgType) + ", templateCode=" + Arrays.toString(templateCode) + ", contentText="
				+ contentText + ", contentmap=" + contentmap + "]";
	}

//	@Override
//	public String toString() {
//		return "消息内容:" + contentText + "消息类型:" + msgType.toString() + "收件人:" + msgTo.toString() + "系统类型:" + sysFlag;
//
//	}

}
