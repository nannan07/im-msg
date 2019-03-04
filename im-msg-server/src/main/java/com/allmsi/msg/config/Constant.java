package com.allmsi.msg.config;

public class Constant {

	// 发送方式
	public static final String SEND_DING = "ding";
	public static final String SEND_SMS = "sms";
	public static final String SEND_MAIL = "mail";
	public static final String SEND_SYSTEM_PC = "systemPC";
	public static final String SEND_SYSTEM_DING = "systemDing";

	// mq订阅服务类型
	public static final String MQTOPIC_MSG = "MesTopic";

	public static final String MQTOPIC_LOG = "LogTopic";

	// 日志类型
	public static final String LOG_HTTP = "HTTP";
	public static final String LOG_DB = "DB";
	public static final String LOG_LOGIN = "LOGIN";
	// 消息分类
	public static final String MQTIP_HTTPLOG = "HTTPLogTag";
	public static final String MQTIP_DBLOG = "DBLogTag";

	// 钉钉相关
	public static final String SEND_Message_Url = "https://oapi.dingtalk.com/message/send?access_token=ACCESS_TOKEN";
	public static final String SEND_WorkMessage_Url = "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2?access_token=ACCESS_TOKEN";
	public static final String DING_CORPID = "ding1c25409cfea4303c35c2f4657eb6378f";
	public static final String DING_CORPSECRET = "ZzYo8AG0Mrd0Gt1UbF9rNDfmbbVsjsLYSBWdnyZ3P94cVQvYuCHZTlWCL1X9I5JT";
	public static final String DING_AGENTID = "188809360";

}
