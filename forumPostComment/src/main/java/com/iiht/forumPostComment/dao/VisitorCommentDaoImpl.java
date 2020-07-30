package com.iiht.forumPostComment.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.forumPostComment.dto.VisitorCommentDto;
import com.iiht.forumPostComment.model.VisitorComments;
import com.iiht.forumPostComment.repository.CommentRepository;

@Service
public class VisitorCommentDaoImpl implements VisitorCommentDao
{
	@Autowired
	private CommentRepository repository; 
	//-------------------------------------------------------------------------------------
	public Boolean saveUpdate(VisitorCommentDto visitorComment, String userId, String loginName, String postId, String category) {
		
		VisitorComments newComments = new VisitorComments();
		
		newComments.setId(visitorComment.getId());
		//------------------------------------------------------------------
		newComments.setUserId(userId);
		newComments.setLoginName(loginName);
		newComments.setPostId(postId);
		newComments.setCategory(category);
		//------------------------------------------------------------------
		newComments.setTags(visitorComment.getTags());
		newComments.setCommentInfo(visitorComment.getCommentInfo());
		
		repository.save(newComments);
		return Boolean.TRUE;
	}
	//-------------------------------------------------------------------------------------
	public Boolean deleteVisitorComment(String commentId) {
		repository.deleteById(commentId);
		return true;
	};
	//-------------------------------------------------------------------------------------
	public VisitorCommentDto getCommentByUserPostId(String userId, String postId) {
		VisitorComments comments = repository.findByUserIdAndPostId(userId, postId);
		return getVisitorCommentDto(comments);
	}
	//-------------------------------------------------------------------------------------
	public List<VisitorCommentDto> getVisitorCommentById(String postId) {
		
		List<VisitorComments> comments = repository.findCommentByPostId(postId);

		if(CollectionUtils.isEmpty(comments))
			return null;
		else
			return comments.stream().map(this::getVisitorCommentDto).collect(Collectors.toList());

	};
	//-------------------------------------------------------------------------------------
	public List<VisitorCommentDto> getAllVisitorComments(){

		List<VisitorComments> posts = repository.findAll();
		
		if(CollectionUtils.isEmpty(posts))
			return null;
		else
			return posts.stream().map(this::getVisitorCommentDto).collect(Collectors.toList());
	};
	//----------------------------------------------------------------------------------------------
	public VisitorCommentDto getVisitorCommentDto(VisitorComments comments)	{
		return new VisitorCommentDto(comments.getId(), comments.getUserId(), comments.getLoginName(), comments.getPostId(), comments.getCategory(),	comments.getTags(), comments.getCommentInfo());
	};	
}