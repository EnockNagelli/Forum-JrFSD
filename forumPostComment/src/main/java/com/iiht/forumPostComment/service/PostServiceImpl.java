package com.iiht.forumPostComment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.forumPostComment.dao.VisitorPostDao;
import com.iiht.forumPostComment.dto.VisitorPostDto;

@Service
public class PostServiceImpl implements PostService 
{
	@Autowired
	private VisitorPostDao visitorPostDao;
	//--------------------------------------------------------------------------------
	public Boolean saveUpdate(VisitorPostDto postInput) {
		return visitorPostDao.saveUpdate(postInput);
	}
	//--------------------------------------------------------------------------------
	public Boolean deletePost(String postId) {
		return visitorPostDao.deleteVisitorPost(postId);
	};
	//--------------------------------------------------------------------------------
	public VisitorPostDto getPostById(String postId) {
		return visitorPostDao.getPostById(postId);
	}
	//--------------------------------------------------------------------------------
	public List<VisitorPostDto> getAllPosts() {
		return visitorPostDao.getAllPosts();
	}
}