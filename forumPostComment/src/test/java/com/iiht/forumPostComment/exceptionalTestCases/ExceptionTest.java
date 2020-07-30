package com.iiht.forumPostComment.exceptionalTestCases;

import static com.iiht.forumPostComment.Utils.TestUtils.*;
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

import com.iiht.forumPostComment.Utils.MasterData;
import com.iiht.forumPostComment.controller.CommentController;
import com.iiht.forumPostComment.controller.LikeController;
import com.iiht.forumPostComment.controller.PostController;
import com.iiht.forumPostComment.dto.VisitorCommentDto;
import com.iiht.forumPostComment.dto.VisitorLikeDto;
import com.iiht.forumPostComment.dto.VisitorPostDto;
import com.iiht.forumPostComment.service.CommentServiceImpl;
import com.iiht.forumPostComment.service.LikeServiceImpl;
import com.iiht.forumPostComment.service.PostServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@WebMvcTest({PostController.class, CommentController.class, LikeController.class})
public class ExceptionTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PostServiceImpl postServiceImpl;
	
	@MockBean
	private CommentServiceImpl commentServiceImpl;

	@MockBean
	private LikeServiceImpl likeServiceImpl;

	//----- 1. VisitorPost Exception ----------------------------------------------------------
	@Test
	public void testAddPostException() throws Exception {

		VisitorPostDto visitorPost = MasterData.getVisitorPostDetails();
		visitorPost.setCategory("ct");
		visitorPost.setTags("tg");
		
		String userId = "user1";
		String loginName = "loginName";
		String category = "category";
		
		when(postServiceImpl.saveUpdate(MasterData.getVisitorPostDetails(), userId, loginName, category)).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/savePost")
				.content(MasterData.asJsonString(MasterData.getVisitorPostDetails()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
	}
	//-----------------------------------------------------------------------------------------
	@Test
	public void testDeletePostException() throws Exception {

		when(postServiceImpl.deletePost("1")).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deletePost/1")
				.content(MasterData.asJsonString(MasterData.getVisitorPostDetails()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	(result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? "true" : "false"), exceptionTestFile);
	}
	
	//----- 2. VisitorComment Exception -------------------------------------------------------
	@Test
	public void testAddCommentException() throws Exception {

		VisitorCommentDto visitorComment = MasterData.getVisitorCommentDetails();
		visitorComment.setUserId("user1");
		visitorComment.setLoginName("loginName");
		visitorComment.setPostId("post1");
		visitorComment.setCategory("category");

		String userId = "user1";
		String postId = "post1";
		String loginName = "loginName";
		String category = "category";
		
		when(commentServiceImpl.saveUpdate(MasterData.getVisitorCommentDetails(), userId, loginName, postId, category)).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/postComment")
				.content(MasterData.asJsonString(MasterData.getVisitorCommentDetails()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
	}
	//-----------------------------------------------------------------------------------------
	@Test
	public void testDeleteCommentException() throws Exception {

		when(commentServiceImpl.deleteComment("1")).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteComment/1")
				.content(MasterData.asJsonString(MasterData.getVisitorCommentDetails()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	(result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? "true" : "false"), exceptionTestFile);
	}

	//----- 3. VisitorLike Exception ----------------------------------------------------------
	@Test
	public void testAddLikeException() throws Exception {

		VisitorLikeDto visitorLike = MasterData.getVisitorLikeDetails();
		visitorLike.setPostId("1");
		visitorLike.setCommentId("1");

		String userId = "user1";
		String loginName = "loginName";

		String postId = "post1";
		String commentId = "comment2";
		
		when(likeServiceImpl.saveUpdate(MasterData.getVisitorLikeDetails(), userId, loginName, postId, commentId)).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/postLike")
				.content(MasterData.asJsonString(MasterData.getVisitorLikeDetails()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
	}
	//-------------------------------------------------------------------------------------------
	@Test
	public void testDeleteLikeException() throws Exception {

		when(likeServiceImpl.deleteLike("1")).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteLike/1")
				.content(MasterData.asJsonString(MasterData.getVisitorLikeDetails()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),	(result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? "true" : "false"), exceptionTestFile);
	}	
}