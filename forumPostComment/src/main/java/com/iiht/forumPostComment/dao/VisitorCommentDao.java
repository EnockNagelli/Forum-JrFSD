package com.iiht.forumPostComment.dao;

import java.util.List;

import com.iiht.forumPostComment.dto.VisitorCommentDto;

public interface VisitorCommentDao {
	public Boolean saveUpdate(VisitorCommentDto visitorComment, String userId, String loginName, String postId, String category);
	public Boolean deleteVisitorComment(String commentId);
	//----------------------------------------------------------------------------
	public VisitorCommentDto getCommentByUserPostId(String userId, String postId);
	public List<VisitorCommentDto> getVisitorCommentById(String postId);
	public List<VisitorCommentDto> getAllVisitorComments();
}