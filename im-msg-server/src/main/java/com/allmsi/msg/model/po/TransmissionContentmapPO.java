package com.allmsi.msg.model.po;

import java.util.Date;

public class TransmissionContentmapPO {
	private String id;

	private String tKey;

	private String tValue;

	private String transId;

	private Date cTime;

	private Date uTime;

	private Integer del;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String gettKey() {
		return tKey;
	}

	public void settKey(String tKey) {
		this.tKey = tKey;
	}

	public String gettValue() {
		return tValue;
	}

	public void settValue(String tValue) {
		this.tValue = tValue;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId == null ? null : transId.trim();
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
}