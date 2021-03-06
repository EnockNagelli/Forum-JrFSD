package com.iiht.forumAdmin.functionalTestCases;

import static com.iiht.forumAdmin.Utils.TestUtils.*;
import static org.mockito.Mockito.*;

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

import com.iiht.forumAdmin.Utils.MasterData;
import com.iiht.forumAdmin.controller.AdminController;
import com.iiht.forumAdmin.dto.CategoryDto;
import com.iiht.forumAdmin.service.CategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest(AdminController.class)
public class TestAdminController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CategoryServiceImpl categoryServiceImpl;
	
	//------ Test 1 ------------------------------------------------------------------------------
	@Test
	public void testAddCategory() throws Exception {
		when(categoryServiceImpl.addCategory(MasterData.getCategoryDetails())).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/postCategory")
				.content(MasterData.asJsonString(MasterData.getCategoryDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"),	businessTestFile);
	}
	
	//------ Test 2 ------------------------------------------------------------------------------
	@Test
	public void testViewAllCategories() throws Exception {
		List<CategoryDto> list = new ArrayList<CategoryDto>();
		list.add(MasterData.getCategoryDetails());
		when(categoryServiceImpl.getAllCategories()).thenReturn(list);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllCategories")
				.content(MasterData.asJsonString(MasterData.getCategoryDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}
	
	//------ Test 3 ------------------------------------------------------------------------------
	@Test
	public  void testDeleteCategory() throws Exception {

		when(categoryServiceImpl.deleteCategory("1")).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteCategory/1")
				.content(MasterData.asJsonString(MasterData.getCategoryDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}	
}