package com.allmsi.msg.service;

import java.util.List;

import com.allmsi.msg.model.Message;
import com.allmsi.msg.model.po.TransmissionMsgToPO;

public interface TransmissionMsgToService {

	List<TransmissionMsgToPO> save(Message message);
}
