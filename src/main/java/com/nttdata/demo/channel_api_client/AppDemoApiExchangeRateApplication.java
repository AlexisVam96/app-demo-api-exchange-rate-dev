package com.nttdata.demo.channel_api_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
public class AppDemoApiExchangeRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppDemoApiExchangeRateApplication.class, args);
	}

}
