package com.iiht.forumUserManagement.dto;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
	@Id
	private String id;
	private String role;
	private String firstName;
	private String lastName;
	private String loginName;
	private String password;
	private String email;
	private Integer mobileNo;
	private Boolean inUse;
}