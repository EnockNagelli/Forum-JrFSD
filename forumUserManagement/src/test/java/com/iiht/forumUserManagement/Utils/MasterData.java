package com.iiht.forumUserManagement.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.forumUserManagement.dto.RegisterUserDto;

public class MasterData {

	public static RegisterUserDto getRegisterUserDetails() {

		RegisterUserDto registerUser = new RegisterUserDto();
		
		registerUser.setId("1");
		registerUser.setFirstName("FirstName");
		registerUser.setLastName("LastName");
		registerUser.setLoginName("LoginName");
		registerUser.setPassword("Password");
		registerUser.setEmail("email@gmail.com");
		registerUser.setMobileNo(999999999);
		registerUser.setInUse(true);
		
		return registerUser;
	}

	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}