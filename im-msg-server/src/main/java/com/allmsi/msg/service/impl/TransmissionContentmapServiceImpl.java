package com.allmsi.msg.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allmsi.msg.log.dao.TransmissionContentmapMapper;
import com.allmsi.msg.model.Message;
import com.allmsi.msg.model.po.TransmissionContentmapPO;
import com.allmsi.msg.service.TransmissionContentmapService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class TransmissionContentmapServiceImpl implements TransmissionContentmapService {

	@Autowired
	private TransmissionContentmapMapper transmissionContentmapDao;

	@Override
	public void save(Message message) {
		List<TransmissionContentmapPO> transContentMapList = new ArrayList<>();
		Map<String, String> contMap = message.getContentmap();
		if (contMap != null && contMap.size() > 0) {
			for (String key : contMap.keySet()) {
				if (StrUtil.notEmpty(key)) {
					TransmissionContentmapPO transmissionContentmap = new TransmissionContentmapPO();
					transmissionContentmap.setId(UUIDUtil.getUUID());
					transmissionContentmap.setTransId(message.getId());
					transmissionContentmap.settKey(key);
					transmissionContentmap.settValue(contMap.get(key));
					transContentMapList.add(transmissionContentmap);
				}
			}
		}
		if (transContentMapList != null && transContentMapList.size() > 0) {
			transmissionContentmapDao.insertBatch(transContentMapList);
		}
	}

}
