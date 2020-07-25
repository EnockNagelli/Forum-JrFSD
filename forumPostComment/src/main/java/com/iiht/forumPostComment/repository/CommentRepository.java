package com.iiht.forumPostComment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iiht.forumPostComment.model.VisitorComments;

@Repository
public interface CommentRepository extends MongoRepository<VisitorComments, String>
{
	VisitorComments findCommentByPostId(String postId);
}