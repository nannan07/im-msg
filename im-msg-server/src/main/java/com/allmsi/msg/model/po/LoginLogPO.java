package com.allmsi.msg.model.po;

import java.util.Date;

public class LoginLogPO {
	
	private String id;
	
	private String userId;
	
	private String token;
	
	private String loginName;

	private String userName;
	
	private String loginTime;
	
	private String deptId;
	
	private String deptName;
	
	private Date cTime;
	
	public LoginLogPO(){};
	

	public String getId() { 
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public Date getcTime() {
		return cTime;
	}


	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
	
	
	
	

}