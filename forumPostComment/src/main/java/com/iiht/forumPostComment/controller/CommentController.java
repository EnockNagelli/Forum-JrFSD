package com.iiht.forumPostComment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iiht.forumPostComment.dto.RegisterUserDto;
import com.iiht.forumPostComment.dto.VisitorCommentDto;
import com.iiht.forumPostComment.dto.VisitorPostDto;
import com.iiht.forumPostComment.service.CommentService;

@RestController
public class CommentController																// PORT: 8092 
{
	@Autowired
	private CommentService commentService;

	@Autowired
	private RestTemplate restTemplate;
		
	//---------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/comment")													// 1. WORKING
 	public String homePage(){
 		return "Welcome to Forum Application - Comment Service : You can opinion your 'Comment' on the 'Post'.";
 	}
	//---------------------------------------------------------------------------------------------------
	@PostMapping(value="/comment/newComment/{userId}/{postId}")								// 2. WORKING
	public ResponseEntity<Boolean> addNewComment(@RequestBody VisitorCommentDto visitorCommentDto, @PathVariable String userId, @PathVariable String postId) {

		RegisterUserDto userDto = restTemplate.getForObject("http://FORUM-USER/user/getUserById/"+userId, RegisterUserDto.class);
		String loginName = userDto.getLoginName();
		//-------------------------------------------------------------------------------------
		VisitorPostDto postDto = restTemplate.getForObject("http://FORUM-POST/post/getPostById/"+postId, VisitorPostDto.class);
		String category = postDto.getCategory();
		//-------------------------------------------------------------------------------------
		Boolean value = commentService.saveUpdate(visitorCommentDto, userId, loginName, postId, category);

		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//---------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/comment/deleteComment/{commentId}")							// 3. WORKING
	public ResponseEntity<Boolean> deleteVisitorComment(@PathVariable String commentId) {
		Boolean value = commentService.deleteComment(commentId);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/comment/getCommentByPostId/{postId}")								// 4. WORKING
	public ResponseEntity<List<VisitorCommentDto>> getCommentById(@PathVariable String postId) {
		return new ResponseEntity<List<VisitorCommentDto>>(commentService.getVisitorCommentById(postId), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/comment/getCommentByUserPostId/{userId}/{postId}")								// 4. WORKING
	public ResponseEntity<VisitorCommentDto> getCommentById(@PathVariable String userId, @PathVariable String postId) {
		return new ResponseEntity<VisitorCommentDto>(commentService.getCommentByUserPostId(userId, postId), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/comment/getAllComments", produces = "application/json")			// 5. WORKING
	public ResponseEntity<List<VisitorCommentDto>> getAllVisitorComments() {
		return new ResponseEntity<List<VisitorCommentDto>>(commentService.getAllComments(), HttpStatus.OK);
	}

	//---------------------------------------------------------------------------------------------------
	// SEARCH OPERATIONS
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/search/comment/{postId}")									 // SEARCH 1. WORKING
	public ResponseEntity<List<VisitorCommentDto>> searchCommentByPostId(@PathVariable String postId) {
		return new ResponseEntity<List<VisitorCommentDto>>(commentService.getVisitorCommentById(postId), HttpStatus.OK);
	}
}