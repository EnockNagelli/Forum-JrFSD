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
import org.springframework.web.bind.annotation.RestController;

import com.iiht.forumPostComment.dto.VisitorLikeDto;
import com.iiht.forumPostComment.service.LikeService;

@RestController
public class LikeController													// PORT: 8092 
{
	@Autowired
	private LikeService likeService;
	//-----------------------------------------------------------------------------------
	@PostMapping(value="/postLike")											// 1. WORKING
	public ResponseEntity<Boolean> saveUpdate(@RequestBody VisitorLikeDto visitorLikeDto) {
		Boolean value = likeService.saveUpdate(visitorLikeDto);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//---------------------------------------------------------------------------------------
	@DeleteMapping(value = "/deleteLike/{likeId}")							// 2. WORKING
	public ResponseEntity<Boolean> deleteVisitorLike(@PathVariable String likeId) {
		Boolean value = likeService.deleteLike(likeId);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	//-----------------------------------------------------------------------------------
	@GetMapping(value = "/getLikeByPostId/{postId}")						// 3. WORKING
	public ResponseEntity<List<VisitorLikeDto>> getVisitorLikeByPostId(@PathVariable String postId) {
		return new ResponseEntity<List<VisitorLikeDto>>(likeService.getVisitorLikeByPostId(postId), HttpStatus.OK);
	}
	//-----------------------------------------------------------------------------------
	@GetMapping(value = "/getLikeByCommentId/{commentId}")					// 4. WORKING
	public ResponseEntity<List<VisitorLikeDto>> getVisitorLikeByCommentId(@PathVariable String commentId) {
		return new ResponseEntity<List<VisitorLikeDto>>(likeService.getVisitorLikeByCommentId(commentId), HttpStatus.OK);
	}
	//-----------------------------------------------------------------------------------
	@GetMapping(value = "/getAllLikes", produces = "application/json")		// 5. WORKING
	public ResponseEntity<List<VisitorLikeDto>> getAllVisitorLikes() {
		return new ResponseEntity<List<VisitorLikeDto>>(likeService.getAllLikes(), HttpStatus.OK);
	}

	//------------------------------------------------------------------------------------------
	// SEARCH OPERATIONS
	//------------------------------------------------------------------------------------------
	@GetMapping(value = "/search/like/{postId}")						    // SEARCH 1. WORKING
	public ResponseEntity<List<VisitorLikeDto>> searchLikeByPostId(@PathVariable String postId) {
		return new ResponseEntity<List<VisitorLikeDto>>(likeService.getVisitorLikeByPostId(postId), HttpStatus.OK);
	}
	//------------------------------------------------------------------------------------------
//	@GetMapping(value = "/search/like/{commentId}")						    // SEARCH 2. won't WORK
//	public ResponseEntity<List<VisitorLikeDto>> searchLikeByCommentId(@PathVariable String commentId) {
//		return new ResponseEntity<List<VisitorLikeDto>>(likeService.getVisitorLikeByCommentId(commentId), HttpStatus.OK);
//	}	
}