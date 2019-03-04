package com.allmsi.msg.model.po;

import java.util.Date;

import com.allmsi.msg.model.DBLogMsg;

public class DBLogPO {
	private String id;

	private String className;

	private String methodName;

	private String parameter;

	private String exSql;

	private String status;

	private String costTime;

	private String paramNum;

	private Date cTime;

	public DBLogPO() {
	};

	public DBLogPO(DBLogMsg dBLogMsg) {

		this.id = dBLogMsg.getId();
		this.status = dBLogMsg.getStatus();
		this.cTime = dBLogMsg.getcTime();

	};

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className == null ? null : className.trim();
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName == null ? null : methodName.trim();
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter == null ? null : parameter.trim();
	}

	public String getExSql() {
		return exSql;
	}

	public void setExSql(String exSql) {
		this.exSql = exSql == null ? null : exSql.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCostTime() {
		return costTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime == null ? null : costTime.trim();
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getParamNum() {
		return paramNum;
	}

	public void setParamNum(String paramNum) {
		this.paramNum = paramNum;
	}
}