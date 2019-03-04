package com.allmsi.msg.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.allmsi.msg.config.Constant;
import com.allmsi.msg.log.dao.HttpLogMapper;
import com.allmsi.msg.model.HttpLogMsg;
import com.allmsi.msg.model.po.HttpLogPO;
import com.allmsi.msg.service.BaseLogService;
import com.allmsi.sys.util.UUIDUtil;

@Service(Constant.LOG_HTTP)
public class HttpLogServiceImpl implements BaseLogService {

	@Resource
	private HttpLogMapper httpLogDao;

	@Override
	public void saveLog(String msg) {
		HttpLogMsg httpLogMsg = JSON.parseObject(msg, new TypeReference<HttpLogMsg>() {
		});
		Map<String, Object> map = httpLogMsg.getParam();
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		String prestr = JSONObject.toJSON(map).toString();
		if (("{}").equals(prestr)) {
			prestr = null;
		}
		if (("undefined").equals(httpLogMsg.getUserId())) {
			httpLogMsg.setUserId(null);
		}
		HttpLogPO httpLogPo = new HttpLogPO(httpLogMsg);

		httpLogPo.setId(UUIDUtil.getUUID());
		httpLogPo.setParam(prestr);
		httpLogPo.setParamNum(String.valueOf(map.size()));
		String cl = httpLogPo.getClassName();
		cl = cl.substring(5, cl.length());
		httpLogPo.setClassName(cl);
		System.out.println("MQ--------------保存HTTP日志;当前线程为:" + Thread.currentThread().getName());
		httpLogDao.insertSelective(httpLogPo);
	}

}
