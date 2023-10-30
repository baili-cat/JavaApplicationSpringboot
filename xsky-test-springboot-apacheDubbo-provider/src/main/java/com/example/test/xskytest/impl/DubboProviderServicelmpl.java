package com.example.test.xskytest.impl;

import com.example.test.xskytest.apacheDubboService.DubboProviderService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author baili
 * @date 2022年04月11日7:22 下午
 */
@DubboService(version = "1.0.0")
public class DubboProviderServicelmpl implements DubboProviderService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
