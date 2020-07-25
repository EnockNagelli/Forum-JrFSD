package com.iiht.forumPostComment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.forumPostComment.dto.VisitorPostDto;
import com.iiht.forumPostComment.service.PostService;

@RestController
public class PostController 												// PORT: 8092
{	
	@Autowired
	private PostService postService;
	//-----------------------------------------------------------------------------------
	@RequestMapping (value = "/")											// 1. WORKING
 	public String home () {
 		return "Posting and/or Comment and/or Like application";
 	}
	//-----------------------------------------------------------------------------------
	@PostMapping(value="/savePost")											// 2. WORKING
	public ResponseEntity<Boolean> saveUpdate(@RequestBody VisitorPostDto visitorPostDto) {
		Boolean value = postService.saveUpdate(visitorPostDto);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//-----------------------------------------------------------------------------------
	@DeleteMapping(value = "/deletePost/{postId}")						// 3. WORKING
	public ResponseEntity<Boolean> deleteVisitorPost(@PathVariable String postId) {
		Boolean value = postService.deletePost(postId);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	//-----------------------------------------------------------------------------------
	@GetMapping(value = "/getPostById/{postId}")							// 3. WORKING
	public ResponseEntity<VisitorPostDto> getVisitorByPostId(@PathVariable String postId) {
		return new ResponseEntity<VisitorPostDto>(postService.getPostById(postId), HttpStatus.OK);
	}	
	//-----------------------------------------------------------------------------------
	@GetMapping(value = "/getAllPosts", produces = "application/json")		// 4. WORKING
	public ResponseEntity<List<VisitorPostDto>> getAllVisitorPosts() {
		return new ResponseEntity<List<VisitorPostDto>>(postService.getAllPosts(), HttpStatus.OK);
	}
	//-----------------------------------------------------------------------------------
	@GetMapping(value = "/getDiscussionList")								// 5. WORKING
	public ResponseEntity<Map<String, String>> getAllDiscussions() {
		HashMap<String, String> discussion = new HashMap<String, String>();
		try	{
			discussion = new HashMap<String, String>();
			List<VisitorPostDto> posts = postService.getAllPosts();
			if(!CollectionUtils.isEmpty(posts)) {
				for(VisitorPostDto p : posts) {
					discussion.put(p.getId(), p.getTitle());
				}
			}
			else
				throw new RuntimeException("No Records Found");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		return new ResponseEntity<Map<String, String>>(discussion, HttpStatus.OK);
	}	
}