package com.allmsi.msg.model.po;

import java.util.Date;

import com.allmsi.msg.model.HttpLogMsg;

public class HttpLogPO {
	private String id;

	private String className;

	private String methodName;

	private String uri;

	private String clientIp;

	private String token;

	private String reqMethod;

	private String browserType;

	private String browVersion;

	private String param;

	private String paramNum;

	private Date cTime;

	public HttpLogPO() {
	};

	public HttpLogPO(HttpLogMsg httpLogMsg) {
		this.id = httpLogMsg.getId();
		this.className = httpLogMsg.getClassName();
		this.methodName = httpLogMsg.getMethodName();
		this.uri = httpLogMsg.getUri();
		this.clientIp = httpLogMsg.getIp();
		this.reqMethod = httpLogMsg.getReqMethod();
		this.browserType = httpLogMsg.getBrowserType();
		this.browVersion = httpLogMsg.getBrowVersion();
		this.token =httpLogMsg.getUserId();
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

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri == null ? null : uri.trim();
	}


	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getReqMethod() {
		return reqMethod;
	}

	public void setReqMethod(String reqMethod) {
		this.reqMethod = reqMethod == null ? null : reqMethod.trim();
	}

	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType == null ? null : browserType.trim();
	}

	public String getBrowVersion() {
		return browVersion;
	}

	public void setBrowVersion(String browVersion) {
		this.browVersion = browVersion == null ? null : browVersion.trim();
	}

	public String getParamNum() {
		return paramNum;
	}

	public void setParamNum(String paramNum) {
		this.paramNum = paramNum == null ? null : paramNum.trim();
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
}