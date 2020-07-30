package com.iiht.forumUserManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.forumUserManagement.dto.RegisterUserDto;
import com.iiht.forumUserManagement.service.RegisterUserService;

@RefreshScope
@RestController
public class RegisterUserController 														// PORT: 8093
{
	@Autowired
	private RegisterUserService userService;
	
	//---------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/")															// 1. WORKING
 	public String landingPage() {
 		return "Welcome to Forum Application - User Management Service.";
 	}
	//---------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/user")														// 2. WORKING
 	public String homePage() {
 		return "Welcome to Forum Application - User Management Service : Register your details to avail 'Forum' Application.";
 	}
	//---------------------------------------------------------------------------------------------------
	@PostMapping(value="/user/newUser")														// 3. WORKING
	public ResponseEntity<Boolean> addNewUser(@RequestBody RegisterUserDto registerUserDto) {
		Boolean value = userService.addNewUser(registerUserDto);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//---------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/user/deleteUser/{registerId}")									// 4. WORKING
	public ResponseEntity<Boolean> deleteUser(@PathVariable String registerId) {
		Boolean value = userService.deleteUser(registerId);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//---------------------------------------------------------------------------------------------------
	@PostMapping(value = "/user/resetUserPassword")											// 5. WORKING
	public ResponseEntity<Boolean> resetPassword(@RequestBody RegisterUserDto registerUserDto) {
		Boolean value = userService.resetPassword(registerUserDto);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/user/getUserById/{registerId}")									// 6. WORKING
	public ResponseEntity<RegisterUserDto> getUserById(@PathVariable String registerId) {
		return new ResponseEntity<RegisterUserDto>(userService.findByRegisterId(registerId), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/user/getUserByLoginName/{loginName}")								// 7. WORKING
	public ResponseEntity<RegisterUserDto> getUserByLoginName(@PathVariable String loginName) {
		System.out.println("LOGIN NAME : "+loginName);
		return new ResponseEntity<RegisterUserDto>(userService.findByLoginName(loginName), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/user/loginCredentials/{loginName}/{password}")					// 8. WORKING
	public ResponseEntity<RegisterUserDto> getUserByCredentials(@PathVariable String loginName,	@PathVariable String password) {
		return new ResponseEntity<RegisterUserDto>(userService.findByCredentials(loginName, password), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/user/getAllUsers", produces = "application/json")					// 9. WORKING
	public ResponseEntity<List<RegisterUserDto>> getAllRegisteredUsers() {
		return new ResponseEntity<List<RegisterUserDto>>(userService.getAllUsers(), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/user/getAllByRole/{role}")										//10. WORKING
	public ResponseEntity<List<RegisterUserDto>> getUserByRole(@PathVariable String role) {
		return new ResponseEntity<List<RegisterUserDto>>(userService.getAllByRoles(role), HttpStatus.OK);
	}
}