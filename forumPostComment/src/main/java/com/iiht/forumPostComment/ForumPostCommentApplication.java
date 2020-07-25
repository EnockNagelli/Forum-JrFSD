package com.iiht.forumPostComment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//@EnableZuulProxy
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) 
@SpringBootApplication(scanBasePackages= {"com.iiht.forumPostComment"})
public class ForumPostCommentApplication {									// PORT: 8092

	public static void main(String[] args) {
		SpringApplication.run(ForumPostCommentApplication.class, args);
	}
}