package com.iiht.forumUserManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class RegisterUserController 													// PORT: 8093
{
	@Autowired
	private RegisterUserService userService;
	//-----------------------------------------------------------------------------------------------
	@RequestMapping (value = "/")														// 1. WORKING
 	public String home () {
 		return "Forum User Management Controller application";
 	}
	//-----------------------------------------------------------------------------------------------
	@PostMapping(value="/newUserRegistration")											// 2. WORKING
	public ResponseEntity<Boolean> addNewUser(@RequestBody RegisterUserDto registerUserDto) {
		Boolean value = userService.addNewUser(registerUserDto);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//-----------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/deleteUser/{registerId}")									// 3. WORKING
	public ResponseEntity<Boolean> deleteUser(@PathVariable String registerId) {
		Boolean value = userService.deleteUser(registerId);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//-----------------------------------------------------------------------------------------------
	@PostMapping(value = "/resetPassword")												// 4. WORKING
	public ResponseEntity<Boolean> resetPassword(@RequestBody RegisterUserDto registerUserDto) {
		Boolean value = userService.resetPassword(registerUserDto);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//-----------------------------------------------------------------------------------------------
	@GetMapping(value = "/getUserById/{registerId}")									// 5. WORKING
	public ResponseEntity<RegisterUserDto> getUserById(@PathVariable String registerId) {
		return new ResponseEntity<RegisterUserDto>(userService.findByRegisterId(registerId), HttpStatus.OK);
	}
	//-----------------------------------------------------------------------------------------------
	@GetMapping(value = "/getUserByLoginName/{loginName}")								// 6. WORKING
	public ResponseEntity<RegisterUserDto> getUserByLoginName(@PathVariable String loginName) {
		System.out.println("LOGIN NAME : "+loginName);
		return new ResponseEntity<RegisterUserDto>(userService.findByLoginName(loginName), HttpStatus.OK);
	}
	//-----------------------------------------------------------------------------------------------
	//@PostMapping(value = "/getUserByCredentials")										// 7. WORKING
	//public ResponseEntity<RegisterUserDto> getUserByCredentials(@RequestBody RegisterUserDto registerUserDto) {
	//	return new ResponseEntity<RegisterUserDto>(userService.findByCredentials(registerUserDto.getLoginName(), registerUserDto.getPassword()), HttpStatus.OK);
	//}
	
	@GetMapping(value = "/loginCredentials/{loginName}/{password}")						// 7. WORKING
	public ResponseEntity<RegisterUserDto> getUserByCredentials(@PathVariable String loginName,	@PathVariable String password) {
		return new ResponseEntity<RegisterUserDto>(userService.findByCredentials(loginName, password), HttpStatus.OK);
	}
	//-----------------------------------------------------------------------------------------------
	@GetMapping(value = "/getAllUsers", produces = "application/json")					// 8. WORKING
	public ResponseEntity<List<RegisterUserDto>> getAllRegisteredUsers() {
		return new ResponseEntity<List<RegisterUserDto>>(userService.getAllUsers(), HttpStatus.OK);
	}
}