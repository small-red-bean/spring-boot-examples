package com.redbean.dubbo.consumer;

import com.redbean.dubbo.service.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class DubboConsumerApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DubboConsumerApplication.class, args);

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-consumer.xml"});
		context.start();
		DemoService demoService = (DemoService) context.getBean("demoService");

		while (true) {
			try {
				Thread.sleep(1000);
				demoService.say("hello world"); // call remote method
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		}
	}
}
