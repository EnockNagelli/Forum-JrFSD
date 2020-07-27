package com.iiht.forumUserManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) 
@SpringBootApplication(scanBasePackages= {"com.iiht.forumUserManagement"})
public class ForumUserManagementApplication {								// PORT: 8093
	public static void main(String[] args) {
		SpringApplication.run(ForumUserManagementApplication.class, args);
	}
}