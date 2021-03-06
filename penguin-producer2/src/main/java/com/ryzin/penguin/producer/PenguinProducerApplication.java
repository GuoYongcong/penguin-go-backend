package com.ryzin.penguin.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient // 开启服务发现支持
@SpringBootApplication
public class PenguinProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PenguinProducerApplication.class, args);
	}
}
