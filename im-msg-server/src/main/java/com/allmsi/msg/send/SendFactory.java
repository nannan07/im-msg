package com.allmsi.msg.send;

import org.springframework.stereotype.Component;

import com.allmsi.sys.config.SpringContextRegister;

@Component
public class SendFactory {

	private final SpringContextRegister springContextRegister;

	// 构造注入
	public SendFactory(SpringContextRegister springContextRegister) {
		this.springContextRegister = springContextRegister;
	};

	public BaseSend getInstance(String type) {
		BaseSend send = null;
		try {
			// System.out.println(springContextRegister);
			// System.out.println(type);
			send = this.springContextRegister.getServiceImpl(BaseSend.class, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return send;
	}

}
