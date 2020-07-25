package com.iiht.forumUserManagement.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("RegisterUser")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String loginName;
	private String password;
	private String email;
	private Integer mobileNo;
	private Boolean inUse;
}