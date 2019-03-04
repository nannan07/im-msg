package com.allmsi.msg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allmsi.msg.config.Constant;
import com.allmsi.msg.model.vo.LogMsgVO;
import com.allmsi.msg.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private DBServiceImpl dBServiceImpl;
	@Autowired
	private HttpLogServiceImpl httpLogServiceImpl;
	@Autowired
	private LoginLogServiceImpl loginLogServiceImpl;

	@Override
	public void saveLog(LogMsgVO log) {
		if (log != null) {
			String logType = log.getType();
			String logStr = log.getLogStr();
			// 按照类型相应处理
			switch (logType) {
			case Constant.LOG_DB:
				dBServiceImpl.saveLog(logStr);
				break;
			case Constant.LOG_HTTP:
				httpLogServiceImpl.saveLog(logStr);
				break;
			case Constant.LOG_LOGIN:
				loginLogServiceImpl.saveLog(logStr);
				break;
			}
		}
	}
}
