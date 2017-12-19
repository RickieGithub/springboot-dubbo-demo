package com.test.boot;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.service.provider.CenterUserService;
import com.service.provider.entity.ReturnS;
import com.test.boot.EchoService;

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

	@RequestMapping("a/{id}")
	public Object ss(@PathVariable String id) {
		ReturnS returns = centerUserService.getCenterUser(27l, 3l);
		LOG.info(echoService.echo(id));
		return returns;
		// return echoService.echo(id);
	}

}
