package com.iiht.forumUserManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.forumUserManagement.dao.RegisterUserDao;
import com.iiht.forumUserManagement.dto.RegisterUserDto;

@Service
public class RegisterUserServiceImpl implements RegisterUserService 
{
	@Autowired
	private RegisterUserDao registerUserDao;
	//------------------------------------------------------------------------------------------
	public Boolean addNewUser(RegisterUserDto registerUserDto) {
		registerUserDao.addUser(registerUserDto);
		return Boolean.TRUE;
	};
	//------------------------------------------------------------------------------------------
	public Boolean deleteUser(String registerId) {
		registerUserDao.deleteUser(registerId);
		return Boolean.TRUE;
	};
	//------------------------------------------------------------------------------------------
	public Boolean resetPassword(RegisterUserDto registerUserDto) {
		registerUserDao.resetPassword(registerUserDto);
		return Boolean.TRUE;
	};
	//------------------------------------------------------------------------------------------
	public RegisterUserDto findByRegisterId(String registerId) {
		return registerUserDao.findByRegisterId(registerId);
	};
	//------------------------------------------------------------------------------------------
	public List<RegisterUserDto> getAllByRoles(String role) {
		return registerUserDao.getAllByRoles(role);
	};
	//------------------------------------------------------------------------------------------
	public RegisterUserDto findByLoginName(String loginName) {
		return registerUserDao.findByLoginName(loginName);
	};
	//------------------------------------------------------------------------------------------
	public RegisterUserDto findByCredentials(String loginName, String password) {
		return registerUserDao.findByCredentials(loginName, password);
	};
	//------------------------------------------------------------------------------------------
	public List<RegisterUserDto> getAllUsers() {
		return registerUserDao.getAllUsers();
	};
}