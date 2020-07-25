package com.iiht.forumPostComment.functionalTestCases;

import static com.iiht.forumPostComment.Utils.TestUtils.*;
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

import com.iiht.forumPostComment.Utils.MasterData;
import com.iiht.forumPostComment.controller.PostController;
import com.iiht.forumPostComment.dto.VisitorPostDto;
import com.iiht.forumPostComment.service.PostServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest(PostController.class)
public class TestVisitorPostController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PostServiceImpl postServiceImpl;
	
	//------ Test 1 ------------------------------------------------------------------------------
	@Test
	public void testAddVisitorPost() throws Exception {
		
		when(postServiceImpl.saveUpdate(MasterData.getVisitorPostDetails())).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/savePost")
				.content(MasterData.asJsonString(MasterData.getVisitorPostDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"),	businessTestFile);
	}
	
	//------ Test 2 ------------------------------------------------------------------------------
	@Test
	public void testViewAllPosts() throws Exception {
		List<VisitorPostDto> list = new ArrayList<VisitorPostDto>();
		list.add(MasterData.getVisitorPostDetails());
		when(postServiceImpl.getAllPosts()).thenReturn(list);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllPosts")
				.content(MasterData.asJsonString(MasterData.getVisitorPostDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}
	
	//------ Test 3 ------------------------------------------------------------------------------
	@Test
	public  void testDeletePost() throws Exception {

		when(postServiceImpl.deletePost("1")).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deletePost/1")
				.content(MasterData.asJsonString(MasterData.getVisitorPostDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}
	
	//------ Test 4 ------------------------------------------------------------------------------
	@Test
	public  void testVisitorPostById() throws Exception{
		
		when(postServiceImpl.getPostById("1")).thenReturn(MasterData.getVisitorPostDetails());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/getPostById/1")
				.content(MasterData.asJsonString(MasterData.getVisitorPostDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}
}