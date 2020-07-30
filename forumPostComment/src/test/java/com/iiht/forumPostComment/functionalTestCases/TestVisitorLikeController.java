package com.iiht.forumPostComment.functionalTestCases;

import static com.iiht.forumPostComment.Utils.TestUtils.businessTestFile;
import static com.iiht.forumPostComment.Utils.TestUtils.currentTest;
import static com.iiht.forumPostComment.Utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

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
import com.iiht.forumPostComment.controller.LikeController;
import com.iiht.forumPostComment.dto.VisitorLikeDto;
import com.iiht.forumPostComment.service.LikeServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest(LikeController.class)
public class TestVisitorLikeController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LikeServiceImpl likeServiceImpl;

	//------ Test 1 ------------------------------------------------------------------------------
	@Test
	public void testAddLike() throws Exception {
		
		String userId = "user1";
		String loginName = "loginName";

		String postId = "post1";
		String commentId = "comment2";
		
		when(likeServiceImpl.saveUpdate(MasterData.getVisitorLikeDetails(), userId, loginName, postId, commentId)).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/postLike")
				.content(MasterData.asJsonString(MasterData.getVisitorLikeDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"),	businessTestFile);
	}
	
	//------ Test 2 ------------------------------------------------------------------------------
	@Test
	public void testViewAllLikes() throws Exception {
		
		List<VisitorLikeDto> list = new ArrayList<VisitorLikeDto>();
		
		list.add(MasterData.getVisitorLikeDetails());
		
		when(likeServiceImpl.getAllLikes()).thenReturn(list);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllLikes")
				.content(MasterData.asJsonString(MasterData.getVisitorLikeDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}
	
	//------ Test 3 ------------------------------------------------------------------------------
	@Test
	public void testDeleteLike() throws Exception {

		when(likeServiceImpl.deleteLike("1")).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteLike/1")
				.content(MasterData.asJsonString(MasterData.getVisitorLikeDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}
	
	//------ Test 4 ------------------------------------------------------------------------------
	@Test
	public void testVisitorLikeById() throws Exception{

		List<VisitorLikeDto> list = new ArrayList<VisitorLikeDto>();
		
		list.add(MasterData.getVisitorLikeDetails());
		
		when(likeServiceImpl.getVisitorLikeByPostId("1")).thenReturn(list);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/getLikeByPostId/1")
				.content(MasterData.asJsonString(MasterData.getVisitorLikeDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}
}
