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
	// SERVICE OPERATIONS
	//--------------------------------------------------------------------------------
	public Boolean saveUpdate(VisitorPostDto postInput, String userId, String loginName, String category) {
		return visitorPostDao.saveUpdate(postInput, userId, loginName, category);
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
	
	//----------------------------------------------------------------
	// SEARCH OPERATIONS
	//----------------------------------------------------------------
	//public List<VisitorPostDto> searchByPostId(String postId) {
	//	return visitorPostDao.getPostById(postId);
	//};
	//--------------------------------------------------------------------------------
	public List<VisitorPostDto> searchByCategory(String category) {
		return visitorPostDao.getAllPostsByCategory(category);
	};
	//--------------------------------------------------------------------------------
	public List<VisitorPostDto> searchByTitle(String title) {
		return visitorPostDao.getAllPostsByTitle(title);
	};
	//--------------------------------------------------------------------------------
	public List<VisitorPostDto> searchByTag(String tag) {
		return visitorPostDao.getAllPostsByTag(tag);
	};	
}