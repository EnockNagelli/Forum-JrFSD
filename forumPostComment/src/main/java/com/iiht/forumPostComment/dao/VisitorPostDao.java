package com.iiht.forumPostComment.dao;

import java.util.List;

import com.iiht.forumPostComment.dto.VisitorPostDto;

public interface VisitorPostDao 
{
	public Boolean saveUpdate(VisitorPostDto postInput); 
	public Boolean deleteVisitorPost(String postId);
	public VisitorPostDto getPostById(String postId);
	public List<VisitorPostDto> getAllPosts();
}
