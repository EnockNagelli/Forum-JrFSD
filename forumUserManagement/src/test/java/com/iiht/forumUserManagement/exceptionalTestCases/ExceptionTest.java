package com.iiht.forumUserManagement.exceptionalTestCases;

import static org.mockito.Mockito.*;

import static com.iiht.forumUserManagement.Utils.TestUtils.*;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.forumUserManagement.Utils.MasterData;
import com.iiht.forumUserManagement.controller.RegisterUserController;
import com.iiht.forumUserManagement.dto.RegisterUserDto;
import com.iiht.forumUserManagement.service.RegisterUserServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest(RegisterUserController.class)
public class ExceptionTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RegisterUserServiceImpl registerUserServiceImpl;
	
	@Test
	public void testAddUserException() throws Exception {

		RegisterUserDto userData = MasterData.getRegisterUserDetails();
		userData.setMobileNo(123);
		userData.setFirstName("ab");
		when(registerUserServiceImpl.addNewUser(MasterData.getRegisterUserDetails())).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/newUserRegistration")
				.content(MasterData.asJsonString(MasterData.getRegisterUserDetails()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
	}

	@Test
	public void testDeleteUserException() throws Exception {

		when(registerUserServiceImpl.deleteUser("1")).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteGiftCards/2")
				.content(MasterData.asJsonString(MasterData.getRegisterUserDetails()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	(result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? "true" : "false"), exceptionTestFile);
	}
}