package com.allmsi.msg.model.po;

import com.allmsi.msg.model.Message;

public class ScheduleMessagePO {
	private String id;

	private String objId;

	private String transId;

	private String templateType;

	private String sysFlag;

	private String msgFrom;

	private String subject;

	private String url;

	private String msgTo;

	private String msgType;

	private String contentText;

	public ScheduleMessagePO() {

	}

	public ScheduleMessagePO(TransmissionMsgToPO transMsgToPO, Message msgBean) {
		if (transMsgToPO != null && msgBean != null) {
			this.id = transMsgToPO.getTransId();
			this.objId = transMsgToPO.getObjId();
			this.transId=transMsgToPO.getTransId();
			this.templateType=msgBean.getTemplateType();
			this.msgFrom = msgBean.getMsgFrom();
			this.msgTo = transMsgToPO.getMsgTo();
			this.msgType = transMsgToPO.getMsgType();
			this.sysFlag = msgBean.getSysFlag();
			this.subject = msgBean.getSubject();
			this.contentText = transMsgToPO.getContentText();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag == null ? null : sysFlag.trim();
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom == null ? null : msgFrom.trim();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject == null ? null : subject.trim();
	}

	public String getMsgTo() {
		return msgTo;
	}

	public void setMsgTo(String msgTo) {
		this.msgTo = msgTo == null ? null : msgTo.trim();
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType == null ? null : msgType.trim();
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}