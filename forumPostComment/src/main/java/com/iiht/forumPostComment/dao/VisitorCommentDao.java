package com.iiht.forumPostComment.dao;

import java.util.List;

import com.iiht.forumPostComment.dto.VisitorCommentDto;

public interface VisitorCommentDao {
	public Boolean saveUpdate(VisitorCommentDto commentInput);
	public Boolean deleteVisitorComment(String commentId);
	public List<VisitorCommentDto> getVisitorCommentById(String postId);
	public List<VisitorCommentDto> getAllVisitorComments();
}