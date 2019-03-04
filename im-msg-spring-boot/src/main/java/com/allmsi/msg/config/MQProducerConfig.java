package com.allmsi.msg.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import com.alibaba.fastjson.JSONObject;
import com.allmsi.msg.model.LogMessage;
import com.allmsi.sys.util.UUIDUtil;

public class MQProducerConfig {
	public static final String TOPIC_LOG = "LogTopic";

	public static final String MQTOPIC_MSG = "MesTopic";

	public static final String LOGTYPE_HTTP = "HTTP";

	public static final String LOGTYPE_LOGIN = "LOGIN";

	public static final String LOGTYPE_DB = "DB";

	public static void sendLog(String producerGroup, String namesrvAddr, String topic, String logType, String logStr) {
		/**
		 * 生产者的组名
		 */
		// 生产者的组名
		DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
		// 指定NameServer地址，多个地址以 ; 隔开
		producer.setNamesrvAddr(namesrvAddr);
		producer.setInstanceName(UUIDUtil.getUUID());
		producer.setVipChannelEnabled(false);
		try {
			/**
			 * Producer对象在使用之前必须要调用start初始化，初始化一次即可 注意：切记不可以在每次发送消息时，都调用start方法
			 */
			producer.start();
			
			if(TOPIC_LOG.equals(topic)) {
				LogMessage logMsg = new LogMessage();
				logMsg.setType(logType);
				logMsg.setLogStr(logStr);
				logStr = JSONObject.toJSONString(logMsg);
			}
			Message message = new Message(topic, logStr.getBytes(RemotingHelper.DEFAULT_CHARSET));
			producer.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.shutdown();
		}
	}

}
