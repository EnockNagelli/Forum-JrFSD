package com.iiht.forumZuulProxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.iiht.forumZuulProxy.filter.ErrorFilter;
import com.iiht.forumZuulProxy.filter.PostFilter;
import com.iiht.forumZuulProxy.filter.PreFilter;
import com.iiht.forumZuulProxy.filter.RouteFilter;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ForumZuulProxyApplication {												// PORT: 8777

	public static void main(String[] args) {
		SpringApplication.run(ForumZuulProxyApplication.class, args);
	}
}








/*
 * @Bean public PreFilter preFilter() { return new PreFilter(); }
 * 
 * @Bean public PostFilter postFilter() { return new PostFilter(); }
 * 
 * @Bean public ErrorFilter errorFilter() { return new ErrorFilter(); }
 * 
 * @Bean public RouteFilter routeFilter() { return new RouteFilter(); }
 */




//@Bean
//public UserDetailsService userDetailsService() {
// UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
//  .build();
// return new InMemoryUserDetailsManager(user);
//}