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

import com.iiht.forumPostComment.dto.VisitorCommentDto;
import com.iiht.forumPostComment.service.CommentService;

@RestController
public class CommentController													// PORT: 8092 
{
	@Autowired
	private CommentService commentService;
	//---------------------------------------------------------------------------------------
	@PostMapping(value="/postComment")											// 1. WORKING
	public ResponseEntity<Boolean> saveUpdate(@RequestBody VisitorCommentDto visitorCommentDto) {
		Boolean value = commentService.saveUpdate(visitorCommentDto);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//---------------------------------------------------------------------------------------
	@DeleteMapping(value = "/deleteComment/{commentId}")						// 3. WORKING
	public ResponseEntity<Boolean> deleteVisitorComment(@PathVariable String commentId) {
		Boolean value = commentService.deleteComment(commentId);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	//---------------------------------------------------------------------------------------
	@GetMapping(value = "/getCommentByPostId/{postId}")							// 2. WORKING
	public ResponseEntity<VisitorCommentDto> getCommentById(@PathVariable String postId) {
		return new ResponseEntity<VisitorCommentDto>(commentService.getVisitorCommentById(postId), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------
	@GetMapping(value = "/getAllComments", produces = "application/json")		// 3. WORKING
	public ResponseEntity<List<VisitorCommentDto>> getAllVisitorComments() {
		return new ResponseEntity<List<VisitorCommentDto>>(commentService.getAllComments(), HttpStatus.OK);
	}
}