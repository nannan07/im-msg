package com.allmsi.msg.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendLogMsgThread {

	ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);

	/**
	 * 生产者的组名
	 */
	@Value("${apache.rocketmq.producer.producerGroup}")
	private String producerGroup;

	/**
	 * NameServer 地址
	 */
	@Value("${apache.rocketmq.namesrvAddr}")
	private String namesrvAddr;

	public void sendLogToMQ(String topic, String logType, String logStr) {
		newFixedThreadPool.execute(new ThreadForPools(topic, logType, logStr));
	}

	class ThreadForPools implements Runnable {

		String topic;

		String logType;

		String logStr;

		public ThreadForPools(String topic, String logType, String logStr) {
			this.topic = topic;
			this.logType = logType;
			this.logStr = logStr;
		}

		@Override
		public void run() {
			MQProducerConfig.sendLog(producerGroup, namesrvAddr, topic, logType, logStr);
		}

	}

}
