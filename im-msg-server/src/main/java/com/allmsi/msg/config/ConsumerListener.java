package com.allmsi.msg.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.allmsi.msg.service.MsgProcessingService;
import com.allmsi.msg.util.RunTimeUtil;

@Component
public class ConsumerListener {

	@Autowired
	private MsgProcessingService msgProcessingService;

	/**
	 * 消息处理消费者的组名
	 */
	@Value("${apache.rocketmq.consumer.PushConsumer}")
	private String consumerGroup;

	@Value("${mqThreadPool.MinNum}")
	private int minNum;
	/**
	 * NameServer地址
	 */
	@Value("${apache.rocketmq.namesrvAddr}")
	private String namesrvAddr;

	@PostConstruct
	public void defaultMQPushConsumer() {

		// 消息消费者的组名
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);

		// 指定NameServer地址
		consumer.setNamesrvAddr(namesrvAddr);

		// 开启vip通道; 不开启的话会报链接错误
		consumer.setVipChannelEnabled(false);

		// 设置mq最小线程数
		consumer.setConsumeThreadMin(minNum);
		// 设置消费者单例
		consumer.setInstanceName(RunTimeUtil.getRocketMqUniqeInstanceName());
		try {
			consumer.subscribe(Constant.MQTOPIC_MSG, "*");// 订阅"消息主题"的消息
			consumer.subscribe(Constant.MQTOPIC_LOG, "*");// 订阅"日志主题"的消息
			// 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
			// 如果非第一次启动，那么按照上次消费的位置继续消费
			consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
			// 开始监听:
			// consumer.registerMessageListener(new RocketMQListener());
			System.out.println("开始监听消息!");
			consumer.registerMessageListener(new MessageListenerConcurrently() {

				@Override
				public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
						ConsumeConcurrentlyContext context) {
					for (MessageExt message : msgs) {
						try {
							// 按照业务逻辑选取业务系统发来消息的类别
							if (message.getTopic().equals(Constant.MQTOPIC_MSG)) {
								// 获取消息
								String msg = new String(message.getBody());
								System.err.println("源数据为:" + msg.toString());
								// 消息处理
								msgProcessingService.messageProcessing(msg);
							} else if (message.getTopic().equals(Constant.MQTOPIC_LOG)) {
								// 获取消息
								String msg = new String(message.getBody());
								System.err.println("源数据为:" + msg.toString());
								// 消息处理
								msgProcessingService.logProcessing(msg);
							} else {
								// 其他类型消息退回消息队列
								return ConsumeConcurrentlyStatus.RECONSUME_LATER;
							}
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("异常消息为:" + new String(message.getBody()));
							return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
						}
					}
					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
				}
			});

			consumer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
