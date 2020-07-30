package com.iiht.forumPostComment.dao;

import java.util.List;

import com.iiht.forumPostComment.dto.VisitorPostDto;

public interface VisitorPostDao {
	
	//----------------------------------------------------------------
	// SERVICE OPERATIONS
	//----------------------------------------------------------------
	public Boolean saveUpdate(VisitorPostDto postInput, String userId, String loginName, String category); 
	public Boolean deleteVisitorPost(String postId);
	public List<VisitorPostDto> getAllPosts();
	
	//----------------------------------------------------------------
	// SEARCH OPERATIONS
	//----------------------------------------------------------------
	public VisitorPostDto getPostById(String postId);
	public List<VisitorPostDto> getAllPostsByCategory(String category);
	public List<VisitorPostDto> getAllPostsByTitle(String title);
	public List<VisitorPostDto> getAllPostsByTag(String tag);
}