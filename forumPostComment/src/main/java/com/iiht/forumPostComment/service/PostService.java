package com.iiht.forumPostComment.service;

import java.util.List;

import com.iiht.forumPostComment.dto.VisitorPostDto;

public interface PostService {
	public Boolean saveUpdate(VisitorPostDto postInput); 
	public Boolean deletePost(String postId);
	public VisitorPostDto getPostById(String postId);
	public List<VisitorPostDto> getAllPosts();
}
