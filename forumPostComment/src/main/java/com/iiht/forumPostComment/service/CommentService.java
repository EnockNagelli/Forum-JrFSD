package com.iiht.forumPostComment.service;

import java.util.List;

import com.iiht.forumPostComment.dto.VisitorCommentDto;

public interface CommentService {
	public Boolean saveUpdate(VisitorCommentDto visitorComment, String userId, String loginName, String postId, String category);
	public Boolean deleteComment(String commentId);
	//-------------------------------------------------------------------------------------------
	public VisitorCommentDto getCommentByUserPostId(String userId, String postId);
	public List<VisitorCommentDto> getVisitorCommentById(String postId);
	public List<VisitorCommentDto> getAllComments();
}
