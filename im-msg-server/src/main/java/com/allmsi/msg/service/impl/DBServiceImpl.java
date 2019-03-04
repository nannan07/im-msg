package com.allmsi.msg.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.allmsi.msg.config.Constant;
import com.allmsi.msg.log.dao.DBLogMapper;
import com.allmsi.msg.model.DBLogMsg;
import com.allmsi.msg.model.po.DBLogPO;
import com.allmsi.msg.service.BaseLogService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service(Constant.LOG_DB)
public class DBServiceImpl implements BaseLogService {

	@Resource
	private DBLogMapper dBLogDao;

	@Override
	public void saveLog(String msg) {
		DBLogMsg dBLogMsg = JSON.parseObject(msg, new TypeReference<DBLogMsg>() {
		});
		String className = "";
		String methodName = "";
		if (dBLogMsg != null) {
			String method = dBLogMsg.getMethod();
			if (StrUtil.notEmpty(method)) {
				String[] methodInfo = method.split("\\.");
				if (methodInfo.length > 2) {
					// mapper类名
					className = methodInfo[methodInfo.length - 2];
					// 方法名
					methodName = methodInfo[methodInfo.length - 1];
				}
			}
			// 占位前的sql
			String sql = "";
			String pname = "";
			if (StrUtil.notEmpty(dBLogMsg.getSql())) {
				sql = dBLogMsg.getSql();
				sql = sql.replaceAll("\'", "").replace("\"", "");
				String sp = sql.toLowerCase().replace("call", "");
				if (sp.startsWith("{")) {
					int s = sp.indexOf("{") + 1;
					int e = sp.indexOf("(");
					pname = sp.substring(s, e);
				} else {
					pname = sql.replace('\n', ' ');
				}
			}
			// 参数
			String param = dBLogMsg.getParameter();
			String paramNum = String.valueOf(param.split(",").length);
			DBLogPO dBLog = new DBLogPO(dBLogMsg);
			String costTime = null;
			if (dBLogMsg.getCostTime() != null) {
				long cost = dBLogMsg.getCostTime();
				costTime = cost + "ms";
			} else {
				costTime = "0ms";
			}
			dBLog.setCostTime(costTime);
			dBLog.setClassName(className);
			dBLog.setMethodName(methodName);
			dBLog.setExSql(pname);
			dBLog.setParameter(param);
			dBLog.setParamNum(paramNum);
			dBLog.setId(UUIDUtil.getUUID());
			System.out.println("MQ--------保存DB;当前线程:" + Thread.currentThread().getName());
			dBLogDao.insertSelective(dBLog);
		}
	}

}
