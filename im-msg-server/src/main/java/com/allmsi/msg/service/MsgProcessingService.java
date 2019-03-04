package com.allmsi.msg.service;

import com.allmsi.msg.model.Message;

public interface MsgProcessingService {

	Message messageProcessing(String msg);

	void logProcessing(String msg);

}
