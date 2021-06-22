package com.iiht.forumPostComment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
import org.springframework.web.client.RestTemplate;

import com.iiht.forumPostComment.dto.CategoryDto;
import com.iiht.forumPostComment.dto.RegisterUserDto;
import com.iiht.forumPostComment.dto.VisitorPostDto;

import com.iiht.forumPostComment.service.PostService;

@RefreshScope
@RestController
public class PostController 														// PORT: 8092
{	
	@Autowired
	private PostService postService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//---------------------------------------------------------------------------------------------------
	// SERVICE OPERATIONS
	//---------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/")															// 1. WORKING
 	public String landingPage() {
 		return "Welcome to Forum Application - Post Service : You can Post and/or Comment and/or Like your opinion";
 	}
	//---------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/post")														// 2. WORKING
 	public String homePage(){
 		return "Welcome to Forum Application - Post Service : You can 'Post' your opinion";
 	}
	//---------------------------------------------------------------------------------------------------
	@PostMapping(value="/post/newPost/{userId}/{categoryId}")								// 3. WORKING
	public ResponseEntity<Boolean> addNewPost(@RequestBody VisitorPostDto visitorPostDto, @PathVariable String userId, @PathVariable String categoryId) {

		CategoryDto categoryDto = restTemplate.getForObject("http://FORUM-ADMIN/admin/getCategoryById/"+categoryId, CategoryDto.class);
		String category = categoryDto.getCategory();
		//-----------------------------------------------------------------------------------------
		//RegisterUserDto userDto = restTemplate.getForObject("http://localhost:8093/user/getUserById/"+userId, RegisterUserDto.class);
		RegisterUserDto userDto = restTemplate.getForObject("http://FORUM-USER/user/getUserById/"+userId, RegisterUserDto.class);

		String id = userDto.getId();
		String loginName = userDto.getLoginName();
		//-----------------------------------------------------------------------------------------
		Boolean value = postService.saveUpdate(visitorPostDto, id, loginName, category);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//---------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/post/deletePost/{postId}")						    			// 4. WORKING
	public ResponseEntity<Boolean> deletePost(@PathVariable String postId) {
		Boolean value = postService.deletePost(postId);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/post/getPostById/{postId}")										// 5. WORKING
	public ResponseEntity<VisitorPostDto> getVisitorByPostId(@PathVariable String postId) {
		return new ResponseEntity<VisitorPostDto>(postService.getPostById(postId), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/post/getPostByCategory/{category}")								// 6. WORKING
	public ResponseEntity<List<VisitorPostDto>> getPostByCategory(@PathVariable String category) {
		return new ResponseEntity<List<VisitorPostDto>>(postService.searchByCategory(category), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/post/getAllPosts", produces = "application/json")					// 7. WORKING
	public ResponseEntity<List<VisitorPostDto>> getAllVisitorPosts() {
		return new ResponseEntity<List<VisitorPostDto>>(postService.getAllPosts(), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/post/getDiscussionList")											// 8. WORKING
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

	//--------------------------------------------------------------------------------------------------
	// SEARCH OPERATIONS
	//--------------------------------------------------------------------------------------------------
	@GetMapping(value = "/search/post/{postId}")						    		// SEARCH 1. WORKING
	public ResponseEntity<VisitorPostDto> getVisitorPostByPostId(@PathVariable String postId) {
		return new ResponseEntity<VisitorPostDto>(postService.getPostById(postId), HttpStatus.OK);
	}
//	@GetMapping("/visitorPosts")
//	public ResponseEntity<List<VisitorPosts>> searchForVisitorPosts(@SearchSpec Specification<VisitorPosts, postId> specs) {
//		return new ResponseEntity<List<VisitorPosts>>(postService.findAll(Specification.where(specs)), HttpStatus.OK);
//	}	
	//--------------------------------------------------------------------------------------------------
	@GetMapping(value = "/search/category/{category}")								// SEARCH 2. WORKING
	public ResponseEntity<List<VisitorPostDto>> getVisitorPostByCategory(@PathVariable String category) {
		return new ResponseEntity<List<VisitorPostDto>>(postService.searchByCategory(category), HttpStatus.OK);
	}
	//--------------------------------------------------------------------------------------------------
	@GetMapping(value = "/search/title/{title}")									// SEARCH 3. WORKING
	public ResponseEntity<List<VisitorPostDto>> getVisitorPostByTitle(@PathVariable String title) {
		return new ResponseEntity<List<VisitorPostDto>>(postService.searchByTitle(title), HttpStatus.OK);
	}
	//--------------------------------------------------------------------------------------------------
	@GetMapping(value = "/search/tag/{tag}")										// SEARCH 4. WORKING
	public ResponseEntity<List<VisitorPostDto>> getVisitorPostByTag(@PathVariable String tag) {
		return new ResponseEntity<List<VisitorPostDto>>(postService.searchByTag(tag), HttpStatus.OK);
	}
}



//ResponseEntity<RegisterUserDto> userDto = restTemplate.getForEntity("http://localhost:8093/user/getUserById/"+userId, RegisterUserDto.class);

//ResponseEntity<RegisterUserDto> userDto = restTemplate.getForEntity("http://FORUM-USER-MANAGEMENT-SERVICE/user/getUserById/"+userId, RegisterUserDto.class);

//RegisterUserDto registerObject = userDto.getBody();
