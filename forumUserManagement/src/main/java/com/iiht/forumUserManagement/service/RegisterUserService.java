package com.iiht.forumUserManagement.service;

import java.util.List;

import com.iiht.forumUserManagement.dto.RegisterUserDto;

public interface RegisterUserService {
	public Boolean addNewUser(RegisterUserDto registerUserDto);
	public Boolean deleteUser(String registerId);
	public Boolean resetPassword(RegisterUserDto registerUserDto);
	public RegisterUserDto findByRegisterId(String registerId);
	public RegisterUserDto findByLoginName(String loginName);
	public RegisterUserDto findByCredentials(String loginName, String password);
	public List<RegisterUserDto> getAllUsers();
}