package com.iiht.forumUserManagement.functionalTestCases;

import static org.mockito.Mockito.*;

import static com.iiht.forumUserManagement.Utils.TestUtils.*;

import java.util.ArrayList;
import java.util.List;

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
public class TestRegisterUserController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RegisterUserServiceImpl registerUserServiceImpl;
	
	//------ Test 1 ------------------------------------------------------------------------------
	@Test
	public void testAddRegisterUser() throws Exception {
		when(registerUserServiceImpl.addNewUser(MasterData.getRegisterUserDetails())).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/newUserRegistration")
				.content(MasterData.asJsonString(MasterData.getRegisterUserDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"),	businessTestFile);
	}
	
	//------ Test 2 ------------------------------------------------------------------------------
	@Test
	public void testLoginCredentials() throws Exception {
		when(registerUserServiceImpl.findByCredentials("praveen", "iiht1234")).thenReturn(MasterData.getRegisterUserDetails());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/loginCredentials/praveen/iiht1234")
				.content(MasterData.asJsonString(MasterData.getRegisterUserDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"),	businessTestFile);		
	}

	//------ Test 3 ------------------------------------------------------------------------------
	@Test
	public void testViewAllUsers() throws Exception {
		List<RegisterUserDto> list = new ArrayList<RegisterUserDto>();
		list.add(MasterData.getRegisterUserDetails());
		when(registerUserServiceImpl.getAllUsers()).thenReturn(list);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllUsers")
				.content(MasterData.asJsonString(MasterData.getRegisterUserDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}
	//------ Test 4 ------------------------------------------------------------------------------
	@Test
	public  void testDeleteUsers() throws Exception {

		when(registerUserServiceImpl.deleteUser("1")).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteUser/1")
				.content(MasterData.asJsonString(MasterData.getRegisterUserDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}
	
	//------ Test 5 ------------------------------------------------------------------------------
	@Test
	public  void testUpdateUsers() throws Exception{
		
		when(registerUserServiceImpl.resetPassword(MasterData.getRegisterUserDetails())).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/resetPassword")
				.content(MasterData.asJsonString(MasterData.getRegisterUserDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}
}