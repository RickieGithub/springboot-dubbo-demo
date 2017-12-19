package com.test.boot;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.service.provider.CenterUserService;
import com.service.provider.entity.ReturnS;

/**
 * 
 * @Title: ClientController.java
 * 
 * @Prject: client
 * 
 * @Package: cn.teaey.sprintboot.test
 * 
 * @Description: TODO
 * 
 * @author: ping.wen
 * 
 * @date: 2017年12月18日 下午6:16:53
 * 
 */
@RestController
@RequestMapping("/")
public class ClientController {
	private Logger LOG = Logger.getLogger(getClass());
	@Reference
	private EchoService echoService;
	@Reference
	private CenterUserService centerUserService;

	@Autowired
	private JmsMessagingTemplate jmt;
	@Autowired
	@Qualifier("test.queue1")
	private Queue q1;
	@Autowired
	@Qualifier("test.queue2")
	private Queue q2;
	@Autowired
	private Topic t;

	@RequestMapping("a/{val}")
	public Object ss(@PathVariable String val) {
		ReturnS returns = centerUserService.getCenterUser(27l, 3l);
		LOG.info(echoService.echo(val));

		jmt.convertAndSend(q1, returns.getMsg());
		jmt.convertAndSend(q2, returns.getMsg());
		
		jmt.convertAndSend(t, returns.getMsg());
		
		return returns;
		// return echoService.echo(id);
	}

	@JmsListener(destination = "test.queue1",containerFactory="jmsListenerContainerQueue")
	public void mqReceive(String msg) {
		LOG.info("test.queue1:" + msg);
	}

	@JmsListener(destination = "test.queue2",containerFactory="jmsListenerContainerQueue")
	public void mqReceive2(Object msg) {
		LOG.info("test.queue2:" + msg);
	}
	
	
	@JmsListener(destination = "test.topic",containerFactory="jmsListenerContainerTopic")
	public void mqReceive3(String msg) {
		LOG.info("mqReceive3:" + msg);
	}

	@JmsListener(destination = "test.topic",containerFactory="jmsListenerContainerTopic")
	public void mqReceive4(Object msg) {
		LOG.info("mqReceive4:" + msg);
	}

}
