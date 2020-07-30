package com.iiht.forumPostComment.dao;

import java.util.List;

import com.iiht.forumPostComment.dto.VisitorLikeDto;

public interface VisitorLikeDao {
	public Boolean saveUpdate(VisitorLikeDto visitorLike, String userId, String loginName, String postId, String commentId);
	public Boolean deleteLikeById(String likeId);
	//-----------------------------------------------------------------------------
	public List<VisitorLikeDto> getVisitorLikeByPostId(String postId);
	public List<VisitorLikeDto> getVisitorLikeByCommentId(String postId);
	public List<VisitorLikeDto> getAllVisitorLikes();
}