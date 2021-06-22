package com.iiht.forumAdmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableDiscoveryClient
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication(scanBasePackages = { "com.iiht.forumAdmin" })
public class ForumAdminApplication { 													// PORT: 8091

	public static void main(String[] args) {
		SpringApplication.run(ForumAdminApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/*
	 * @Bean public WebMvcConfigurer configure() { return new WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**").allowedOrigins("http://localhost:4200"); } }; }
	 */

	// ----------------------------------------------------------------------------------------------

	/*
	 * @Bean public CorsFilter corsFilter() { final UrlBasedCorsConfigurationSource
	 * source = new UrlBasedCorsConfigurationSource();
	 * 
	 * final CorsConfiguration config = new CorsConfiguration();
	 * 
	 * config.setAllowCredentials(true); config.addAllowedOrigin("*");
	 * config.addAllowedHeader("*"); config.addAllowedMethod("*");
	 * 
	 * config.addAllowedMethod("OPTIONS"); config.addAllowedMethod("HEAD");
	 * config.addAllowedMethod("GET"); config.addAllowedMethod("PUT");
	 * config.addAllowedMethod("POST"); config.addAllowedMethod("DELETE");
	 * config.addAllowedMethod("PATCH"); source.registerCorsConfiguration("/**",
	 * config);
	 * 
	 * source.registerCorsConfiguration("/**", config); return new
	 * CorsFilter(source); }
	 */
}

/*
Service Discovery is one of the key tenets of a microservice-based architecture. 
Eureka is the Netflix Service Discovery Server and Client. 
The server can be configured and deployed to be highly available, with each server replicating 
state about the registered services to the others.

How to Include Eureka Client
To include the Eureka Client in your project, use the starter with a 
	group ID of 	'org.springframework.cloud' and an 
	artifact ID of 	'spring-cloud-starter-netflix-eureka-client'.


*/