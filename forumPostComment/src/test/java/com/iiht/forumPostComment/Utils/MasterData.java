package com.iiht.forumPostComment.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.forumPostComment.dto.VisitorCommentDto;
import com.iiht.forumPostComment.dto.VisitorLikeDto;
import com.iiht.forumPostComment.dto.VisitorPostDto;

public class MasterData {

	public static VisitorPostDto getVisitorPostDetails() {

		VisitorPostDto post = new VisitorPostDto();
		
		
		post.setId("1");
		post.setUserId("user1");
		post.setLoginName("loginName");
		post.setCategory("Category");
		post.setTitle("Title");
		post.setTags("Tag");
		post.setPostInfo("Great Game. Post Description");

		return post;
	}
	//-----------------------------------------------------------------------------------
	public static VisitorCommentDto getVisitorCommentDetails() {

		VisitorCommentDto comment = new VisitorCommentDto();
		
		comment.setId("1");
		comment.setPostId("1");
		comment.setTags("Tag");
		comment.setCommentInfo("Great Game. Comment Description");
		
		return comment;
	}
	//-----------------------------------------------------------------------------------
	public static VisitorLikeDto getVisitorLikeDetails() {

		VisitorLikeDto like = new VisitorLikeDto();

		like.setId("1");
		like.setPostId("1");
		like.setCommentId("1");
		like.setLikeInfo("I like this sport");

		return like;
	}	
	//-----------------------------------------------------------------------------------
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