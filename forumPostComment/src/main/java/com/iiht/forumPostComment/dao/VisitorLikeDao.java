package com.iiht.forumPostComment.dao;

import java.util.List;

import com.iiht.forumPostComment.dto.VisitorLikeDto;

public interface VisitorLikeDao {
	public Boolean saveUpdate(VisitorLikeDto likeInput);
	public Boolean deleteLikeById(String likeId);
	public VisitorLikeDto getVisitorLikeByPostId(String postId);
	public VisitorLikeDto getVisitorLikeByCommentId(String postId);
	public List<VisitorLikeDto> getAllVisitorLikes();
}