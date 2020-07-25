package com.iiht.forumPostComment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iiht.forumPostComment.model.VisitorLikes;

@Repository
public interface LikeRepository extends MongoRepository<VisitorLikes, String>
{
	VisitorLikes findVisitorLikeByPostId(String postId);
	
	VisitorLikes findVisitorLikeByCommentId(String commentId);
}