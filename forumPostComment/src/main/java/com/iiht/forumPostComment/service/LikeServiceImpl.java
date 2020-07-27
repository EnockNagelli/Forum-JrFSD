package com.iiht.forumPostComment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.forumPostComment.dao.VisitorLikeDao;
import com.iiht.forumPostComment.dto.VisitorLikeDto;

@Service
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	private VisitorLikeDao visitorLikeDao;
	//---------------------------------------------------------------------------------------
	public Boolean saveUpdate(VisitorLikeDto likeInput) {
		return visitorLikeDao.saveUpdate(likeInput);
	}
	//---------------------------------------------------------------------------------------
	public Boolean deleteLike(String likeId) {
		return visitorLikeDao.deleteLikeById(likeId);
	};
	//---------------------------------------------------------------------------------------
	public List<VisitorLikeDto> getVisitorLikeByPostId(String postId){
		return visitorLikeDao.getVisitorLikeByPostId(postId);
	}
	//---------------------------------------------------------------------------------------
	public List<VisitorLikeDto> getVisitorLikeByCommentId(String commentId){
		return visitorLikeDao.getVisitorLikeByCommentId(commentId);
	}
	//---------------------------------------------------------------------------------------
	public List<VisitorLikeDto> getAllLikes() {
		return visitorLikeDao.getAllVisitorLikes();
	};
}