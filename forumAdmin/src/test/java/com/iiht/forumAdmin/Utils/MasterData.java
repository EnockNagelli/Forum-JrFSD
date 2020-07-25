package com.iiht.forumAdmin.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.forumAdmin.dto.CategoryDto;

public class MasterData {

	public static CategoryDto getCategoryDetails() {

		CategoryDto registerUser = new CategoryDto();
		
		registerUser.setId("1");
		registerUser.setAdminName("AdminName");
		registerUser.setCategory("CategoryDescription");
		
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