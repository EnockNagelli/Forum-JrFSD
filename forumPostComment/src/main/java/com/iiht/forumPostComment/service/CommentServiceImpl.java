package com.iiht.forumPostComment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.forumPostComment.dao.VisitorCommentDao;
import com.iiht.forumPostComment.dto.VisitorCommentDto;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private VisitorCommentDao visitorCommentDao;
	//----------------------------------------------------------------------------------
	public Boolean saveUpdate(VisitorCommentDto commentInput) {
		return visitorCommentDao.saveUpdate(commentInput);
	}
	//----------------------------------------------------------------------------------
	public Boolean deleteComment(String commentId) {
		return visitorCommentDao.deleteVisitorComment(commentId);
	};
	//----------------------------------------------------------------------------------
	public VisitorCommentDto getVisitorCommentById(String postId) {
		return visitorCommentDao.getVisitorCommentById(postId);
	}
	//----------------------------------------------------------------------------------
	public List<VisitorCommentDto> getAllComments() {
		return visitorCommentDao.getAllVisitorComments();
	};
}