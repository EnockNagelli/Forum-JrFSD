package com.iiht.forumZuulProxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ForumZuulProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumZuulProxyApplication.class, args);
	}
}