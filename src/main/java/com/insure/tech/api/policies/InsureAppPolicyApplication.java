package com.insure.tech.api.policies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class InsureAppPolicyApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsureAppPolicyApplication.class, args);
	}

}
