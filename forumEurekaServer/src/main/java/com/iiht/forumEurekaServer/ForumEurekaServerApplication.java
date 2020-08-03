package com.iiht.forumEurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ForumEurekaServerApplication {												// PORT: 8761

	public static void main(String[] args) {
		SpringApplication.run(ForumEurekaServerApplication.class, args);
	}	
}