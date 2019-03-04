package com.allmsi.msg.model;

import java.util.Date;

public class DBLogMsg {
	private String id;

	private String method;

	private String parameter;

	private String sql;
	
	private String status;

	private Long costTime;

	private String cUserId;

	private Date cTime;

	public DBLogMsg() {
	};

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter == null ? null : parameter.trim();
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql == null ? null : sql.trim();
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public Long getCostTime() {
		return costTime;
	}

	public void setCostTime(Long costTime) {
		this.costTime = costTime ;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId == null ? null : cUserId.trim();
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}