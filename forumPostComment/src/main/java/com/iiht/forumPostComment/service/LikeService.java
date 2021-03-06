package com.iiht.forumPostComment.service;

import java.util.List;

import com.iiht.forumPostComment.dto.VisitorLikeDto;

public interface LikeService {
	public Boolean saveUpdate(VisitorLikeDto visitorLike, String userId, String loginName, String postId, String commentId);
	public Boolean deleteLike(String likeId);
	//------------------------------------------------------------------------------------
	public List<VisitorLikeDto> getVisitorLikeByPostId(String postId);
	public List<VisitorLikeDto> getVisitorLikeByCommentId(String commentId);
	public List<VisitorLikeDto> getAllLikes();
}