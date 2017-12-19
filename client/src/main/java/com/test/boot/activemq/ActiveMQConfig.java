package com.test.boot.activemq;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @Title: ActiveMQConfig.java
 * 
 * @Prject: client
 * 
 * @Package: com.test.boot.activemq
 * 
 * @Description: TODO
 * 
 * @author: ping.wen
 * 
 * @date: 2017年12月19日 下午3:30:00
 * 
 */
@Configuration
public class ActiveMQConfig {

	@Bean(name = "test.queue1")
	public Queue qq() {
		return new ActiveMQQueue("test.queue1");
	}

	@Bean(name = "test.queue2")
	public Queue qq2() {
		return new ActiveMQQueue("test.queue2");
	}

	/*@Bean
	public Topic t() {
		return new ActiveMQTopic("test.topic");
	}*/
}
