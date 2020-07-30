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
import com.iiht.forumPostComment.dto.VisitorLikeDto;
import com.iiht.forumPostComment.service.LikeService;

@RestController
public class LikeController																	// PORT: 8092
{
	@Autowired
	private LikeService likeService;

	@Autowired
	private RestTemplate restTemplate;
		
	//---------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/like")														// 1. WORKING
 	public String homePage(){
 		return "Welcome to Forum Application - Like Service : You can opinion your 'Like' on the 'Post' or 'Comment'.";
 	}
	//---------------------------------------------------------------------------------------------------
	@PostMapping(value="/like/newLike/{userId}/{postId}")									// 2. WORKING
	public ResponseEntity<Boolean> addNewLike(@RequestBody VisitorLikeDto visitorLikeDto, @PathVariable String userId, @PathVariable String postId) {
		
		RegisterUserDto userDto = restTemplate.getForObject("http://FORUM-USER/user/getUserById/"+userId, RegisterUserDto.class);
		String loginName = userDto.getLoginName();
		//-------------------------------------------------------------------------------------
		VisitorCommentDto commentDto = restTemplate.getForObject("http://FORUM-POST/comment/getCommentByUserPostId/"+userId+"/"+postId, VisitorCommentDto.class);
		String commentId = commentDto.getId();
		//-------------------------------------------------------------------------------------
		Boolean value = likeService.saveUpdate(visitorLikeDto, userId, loginName, postId, commentId);

		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//---------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/like/deleteLike/{likeId}")										// 3. WORKING
	public ResponseEntity<Boolean> deleteVisitorLike(@PathVariable String likeId) {
		Boolean value = likeService.deleteLike(likeId);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/like/getLikeByPostId/{postId}")									// 4. WORKING
	public ResponseEntity<List<VisitorLikeDto>> getVisitorLikeByPostId(@PathVariable String postId) {
		return new ResponseEntity<List<VisitorLikeDto>>(likeService.getVisitorLikeByPostId(postId), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/like/getLikeByCommentId/{commentId}")								// 5. WORKING
	public ResponseEntity<List<VisitorLikeDto>> getVisitorLikeByCommentId(@PathVariable String commentId) {
		return new ResponseEntity<List<VisitorLikeDto>>(likeService.getVisitorLikeByCommentId(commentId), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/like/getAllLikes", produces = "application/json")					// 6. WORKING
	public ResponseEntity<List<VisitorLikeDto>> getAllVisitorLikes() {
		return new ResponseEntity<List<VisitorLikeDto>>(likeService.getAllLikes(), HttpStatus.OK);
	}

	//---------------------------------------------------------------------------------------------------
	// SEARCH OPERATIONS
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/search/like/{postId}")						    		 // SEARCH 1. WORKING
	public ResponseEntity<List<VisitorLikeDto>> searchLikeByPostId(@PathVariable String postId) {
		return new ResponseEntity<List<VisitorLikeDto>>(likeService.getVisitorLikeByPostId(postId), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------------------
//	@GetMapping(value = "/search/like/{commentId}")						    	  // SEARCH 2. won't WORK
//	public ResponseEntity<List<VisitorLikeDto>> searchLikeByCommentId(@PathVariable String commentId) {
//		return new ResponseEntity<List<VisitorLikeDto>>(likeService.getVisitorLikeByCommentId(commentId), HttpStatus.OK);
//	}	
}