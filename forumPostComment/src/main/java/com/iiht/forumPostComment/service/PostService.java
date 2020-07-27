package com.iiht.forumPostComment.service;

import java.util.List;

import com.iiht.forumPostComment.dto.VisitorPostDto;

public interface PostService {
	
	//----------------------------------------------------------------
	// SERVICE OPERATIONS
	//----------------------------------------------------------------
	public Boolean saveUpdate(VisitorPostDto postInput); 
	public Boolean deletePost(String postId);
	public List<VisitorPostDto> getAllPosts();

	//----------------------------------------------------------------
	// SEARCH OPERATIONS
	//----------------------------------------------------------------
	public VisitorPostDto getPostById(String postId);
	public List<VisitorPostDto> searchByCategory(String category);
	public List<VisitorPostDto> searchByTitle(String title);
	public List<VisitorPostDto> searchByTag(String tag);
}