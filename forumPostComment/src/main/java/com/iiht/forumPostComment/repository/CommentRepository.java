package com.iiht.forumPostComment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iiht.forumPostComment.model.VisitorComments;

@Repository
public interface CommentRepository extends MongoRepository<VisitorComments, String>
{
	public VisitorComments findByUserIdAndPostId(String userId, String postId);
	
	public List<VisitorComments> findCommentByPostId(String postId);
}