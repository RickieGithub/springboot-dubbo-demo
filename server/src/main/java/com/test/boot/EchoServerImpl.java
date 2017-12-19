package com.test.boot;

import com.alibaba.dubbo.config.annotation.Service;

import cn.teaey.sprintboot.test.EchoService;

/**
 * @author xiaofei.wxf(teaey)
 * @since 0.0.0
 */
@Service
public class EchoServerImpl implements EchoService {

    public String echo(String str) {
        System.out.println(str);
        return str;
    }
}
