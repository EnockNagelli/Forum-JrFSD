package com.iiht.forumAdmin.exceptionalTestCases;

import static com.iiht.forumAdmin.Utils.TestUtils.*;
import static org.mockito.Mockito.*;

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

import com.iiht.forumAdmin.Utils.MasterData;
import com.iiht.forumAdmin.controller.AdminController;
import com.iiht.forumAdmin.dto.CategoryDto;
import com.iiht.forumAdmin.service.CategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest(AdminController.class)
public class ExceptionTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CategoryServiceImpl categoryServiceImpl;
	
	@Test
	public void testAddCategoryException() throws Exception {

		CategoryDto userData = MasterData.getCategoryDetails();
		userData.setAdminName("ab");
		userData.setCategory("ct");
		when(categoryServiceImpl.addCategory(MasterData.getCategoryDetails())).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/newUserRegistration")
				.content(MasterData.asJsonString(MasterData.getCategoryDetails()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
	}

	@Test
	public void testDeleteCategoryException() throws Exception {

		when(categoryServiceImpl.deleteCategory("1")).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteCategory/1")
				.content(MasterData.asJsonString(MasterData.getCategoryDetails()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	(result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? "true" : "false"), exceptionTestFile);
	}
}