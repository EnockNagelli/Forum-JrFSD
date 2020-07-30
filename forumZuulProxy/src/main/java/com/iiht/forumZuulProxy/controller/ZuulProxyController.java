package com.iiht.forumZuulProxy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZuulProxyController {

	//---------------------------------------------------------------------------------------------------
	// SERVICE OPERATIONS
	//---------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/")															// 1. WORKING
 	public String landingPage() {
 		return "Welcome to Forum Application - ZUUL PROXY Service.";
 	}	
}
