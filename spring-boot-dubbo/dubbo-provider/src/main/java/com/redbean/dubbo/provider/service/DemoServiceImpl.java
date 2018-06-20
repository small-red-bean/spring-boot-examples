package com.redbean.dubbo.provider.service;


import com.redbean.dubbo.service.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public void say(String world) {
        System.out.println("provider say: " + world + "........");
    }
}
