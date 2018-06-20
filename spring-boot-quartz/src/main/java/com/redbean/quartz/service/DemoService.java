package com.redbean.quartz.service;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class DemoService implements Serializable {

    public void sayHello() {
        System.out.println("hello world.......");
    }
}
