package com.iiht.forumUserManagement.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iiht.forumUserManagement.model.RegisterUser;

@Repository
public interface RegisterUserRepository extends MongoRepository<RegisterUser, String>{
	
	public List<RegisterUser> findByRole(String role);
	
	public RegisterUser findByLoginName(String loginName);
	
	public RegisterUser findByLoginNameAndPassword(String loginName, String password);	
}