package com.allmsi.msg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.msg.mes.dao.UserMapper;
import com.allmsi.msg.model.po.UserPO;
import com.allmsi.msg.model.vo.UserVO;
import com.allmsi.msg.service.UserService;
import com.allmsi.sys.util.StrUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userDao;

	@Override
	public UserVO selectUserById(String userId) {
		if (StrUtil.notEmpty(userId)) {
			UserPO user=userDao.selectByPrimaryKey(userId);
			return new UserVO(user);
		}
		return null;
	}

}
