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
import com.iiht.forumPostComment.controller.CommentController;
import com.iiht.forumPostComment.dto.VisitorCommentDto;
import com.iiht.forumPostComment.service.CommentServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest(CommentController.class)
public class TestVisitorCommentController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CommentServiceImpl commentServiceImpl;
	
	//------ Test 1 ------------------------------------------------------------------------------
	@Test
	public void testAddComment() throws Exception {
		
		when(commentServiceImpl.saveUpdate(MasterData.getVisitorCommentDetails())).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/postComment")
				.content(MasterData.asJsonString(MasterData.getVisitorCommentDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"),	businessTestFile);
	}
	
	//------ Test 2 ------------------------------------------------------------------------------
	@Test
	public void testViewAllComments() throws Exception {
		
		List<VisitorCommentDto> list = new ArrayList<VisitorCommentDto>();
		
		list.add(MasterData.getVisitorCommentDetails());
		
		when(commentServiceImpl.getAllComments()).thenReturn(list);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllComments")
				.content(MasterData.asJsonString(MasterData.getVisitorCommentDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}
	
	//------ Test 3 ------------------------------------------------------------------------------
	@Test
	public void testDeleteComment() throws Exception {

		when(commentServiceImpl.deleteComment("1")).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteComment/1")
				.content(MasterData.asJsonString(MasterData.getVisitorCommentDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}
	
	//------ Test 4 ------------------------------------------------------------------------------
	@Test
	public  void testVisitorCommentById() throws Exception{
		
		when(commentServiceImpl.getVisitorCommentById("1")).thenReturn(MasterData.getVisitorCommentDetails());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/getCommentByPostId/1")
				.content(MasterData.asJsonString(MasterData.getVisitorCommentDetails()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value() ? "true" : "false"), businessTestFile);
	}
}
