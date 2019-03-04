package com.allmsi.msg.model.vo;

import java.io.Serializable;
import java.util.Date;

import com.allmsi.msg.model.po.UserPO;


public class UserVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	
    private String loginName;
    
    private String userName;

    private String phone;

    private String email;

    private Integer sex;
    
    private Integer sort;
    
	private Date cTime;

    private String token;
    

  
    public UserVO() {
    	
    }
    
    public UserVO(UserPO user) {
    	if (user != null) {
	    	this.id = user.getId();
	    	this.loginName = user.getLoginName();
	    	this.userName = user.getUserName();
	    	this.phone = user.getPhone();
	    	this.email = user.getEmail();
	    	this.sex = user.getSex();
	    	this.sort = user.getSort();
	    	this.cTime = user.getcTime();
    	}
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
