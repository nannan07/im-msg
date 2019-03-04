package com.allmsi.msg.service;

import com.allmsi.msg.model.Message;
import com.allmsi.msg.model.po.TransmissionPO;

public interface TransmissionService {

	TransmissionPO save(Message message);
	
}
