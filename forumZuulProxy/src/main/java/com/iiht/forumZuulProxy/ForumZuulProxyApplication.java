package com.iiht.forumZuulProxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ForumZuulProxyApplication {													// PORT: 8777

	public static void main(String[] args) {
		SpringApplication.run(ForumZuulProxyApplication.class, args);
	}
	
	@Bean
    public CorsFilter corsFilter() 
	{
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
		/*
		 * config.addAllowedMethod("OPTIONS"); config.addAllowedMethod("HEAD");
		 * config.addAllowedMethod("GET"); config.addAllowedMethod("PUT");
		 * config.addAllowedMethod("POST"); config.addAllowedMethod("DELETE");
		 * config.addAllowedMethod("PATCH");
		 * source.registerCorsConfiguration("/**", config);
		 */
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }	
}










//import com.iiht.forumZuulProxy.filter.ErrorFilter;
//import com.iiht.forumZuulProxy.filter.PostFilter;
//import com.iiht.forumZuulProxy.filter.PreFilter;
//import com.iiht.forumZuulProxy.filter.RouteFilter;

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