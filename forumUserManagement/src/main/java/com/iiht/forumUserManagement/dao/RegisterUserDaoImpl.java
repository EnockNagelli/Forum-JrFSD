package com.iiht.forumUserManagement.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.forumUserManagement.dto.RegisterUserDto;
import com.iiht.forumUserManagement.model.RegisterUser;
import com.iiht.forumUserManagement.repository.RegisterUserRepository;

@Service
public class RegisterUserDaoImpl implements RegisterUserDao {
	
	@Autowired
	private RegisterUserRepository userRepository;
	//-----------------------------------------------------------------------------------------------
	public Boolean addUser(RegisterUserDto registerUserDto) {
		RegisterUser newUser = new RegisterUser(); 
		newUser.setId(registerUserDto.getId());
		newUser.setRole(registerUserDto.getRole());
		newUser.setFirstName(registerUserDto.getFirstName());
		newUser.setLastName(registerUserDto.getLastName());
		newUser.setLoginName(registerUserDto.getLoginName());
		newUser.setPassword(registerUserDto.getPassword());
		newUser.setEmail(registerUserDto.getEmail());
		newUser.setMobileNo(registerUserDto.getMobileNo());
		newUser.setInUse(registerUserDto.getInUse());
		userRepository.insert(newUser);
		return true;
	};
	//-----------------------------------------------------------------------------------------------
	public Boolean deleteUser(String registerId) {
		userRepository.deleteById(registerId);
		return true;
	};
	//-----------------------------------------------------------------------------------------------
	public Boolean resetPassword(RegisterUserDto registerUserDto) {
		// Query query = new Query();
		// query.addCriteria(Criteria.where("id").is(registerUserDto.getId()));
		// Optional<RegisterUser> user = ((MongoRepository<RegisterUser, String>) query).findById(registerUserDto.getId());
		Optional<RegisterUser> user = userRepository.findById(registerUserDto.getId());
		RegisterUser registerUser = user.get();
		registerUser.setPassword(registerUserDto.getPassword());
		userRepository.save(registerUser);
		return true;
	};
	//-----------------------------------------------------------------------------------------------
	public RegisterUserDto findByRegisterId(String registerId) {
		Optional<RegisterUser> fetchUser = userRepository.findById(registerId);
		RegisterUserDto registerUserDto = getRegisterUserDto(fetchUser.get());
		return registerUserDto;
	};
	//-----------------------------------------------------------------------------------------------
	public RegisterUserDto findByLoginName(String loginName) {
		RegisterUser fetchUserByName = userRepository.findByLoginName(loginName);
		RegisterUserDto registerUserDto = getRegisterUserDto(fetchUserByName);
		return registerUserDto;
	};
	//-----------------------------------------------------------------------------------------------
	public RegisterUserDto findByCredentials(String loginName, String password){
		RegisterUser fetchUserByCredentials = userRepository.findByLoginNameAndPassword(loginName, password);
		RegisterUserDto registerUserDto = getRegisterUserDto(fetchUserByCredentials);
		return registerUserDto;
	};
	//-----------------------------------------------------------------------------------------------
	public List<RegisterUserDto> getAllByRoles(String role){
		
		List<RegisterUser> posts = userRepository.findByRole(role);

		if(CollectionUtils.isEmpty(posts))
			return null;
		else
			return posts.stream().map(this::getRegisterUserDto).collect(Collectors.toList());		
	};
	//-----------------------------------------------------------------------------------------------
	public List<RegisterUserDto> getAllUsers() {
		
		List<RegisterUser> posts = userRepository.findAll();

		if(CollectionUtils.isEmpty(posts))
			return null;
		else
			return posts.stream().map(this::getRegisterUserDto).collect(Collectors.toList());
	};
	//-----------------------------------------------------------------------------------------------
	public RegisterUserDto getRegisterUserDto(RegisterUser registerUser) {
		return new RegisterUserDto(registerUser.getId(), registerUser.getRole(), registerUser.getFirstName(), registerUser.getLastName(), registerUser.getLoginName(), registerUser.getPassword(), registerUser.getEmail(), registerUser.getMobileNo(),	registerUser.getInUse());
	};
}