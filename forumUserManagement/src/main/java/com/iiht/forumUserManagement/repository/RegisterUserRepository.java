package com.iiht.forumUserManagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iiht.forumUserManagement.model.RegisterUser;

@Repository
public interface RegisterUserRepository extends MongoRepository<RegisterUser, String>{
	
	public RegisterUser findByLoginName(String loginName);
	
	public RegisterUser findByLoginNameAndPassword(String loginName, String password);	
}