package com.iiht.forumPostComment.service;

import java.util.List;

import com.iiht.forumPostComment.dto.VisitorCommentDto;

public interface CommentService {
	public Boolean saveUpdate(VisitorCommentDto commentInput);
	public Boolean deleteComment(String commentId);
	public List<VisitorCommentDto> getVisitorCommentById(String postId);
	public List<VisitorCommentDto> getAllComments();
}
