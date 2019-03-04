package com.allmsi.msg.model.po;

import java.util.Date;

import com.allmsi.msg.model.Message;

public class TransmissionPO {
	private String id;

	private String sysFlag;

	private String templateType;

	private String msgFrom;

	private String subject;

	private Date cTime;

	private Date uTime;

	private Integer del;

	private String contentText;

	public TransmissionPO() {

	}

	public TransmissionPO(Message message) {
		if (message != null) {
			this.sysFlag = message.getSysFlag();
			this.templateType = message.getTemplateType();
			this.msgFrom = message.getMsgFrom();
			this.subject = message.getSubject();
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

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType == null ? null : templateType.trim();
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

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText == null ? null : contentText.trim();
	}
}