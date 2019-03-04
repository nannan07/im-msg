package com.allmsi.msg.service;

import com.allmsi.msg.model.vo.UserVO;

public interface UserService {
	UserVO  selectUserById(String userId);
	
	

}
