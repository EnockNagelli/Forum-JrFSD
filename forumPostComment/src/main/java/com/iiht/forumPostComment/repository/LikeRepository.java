package com.iiht.forumPostComment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iiht.forumPostComment.model.VisitorLikes;

@Repository
public interface LikeRepository extends MongoRepository<VisitorLikes, String>
{
	List<VisitorLikes> findVisitorLikeByPostId(String postId);
	
	List<VisitorLikes> findVisitorLikeByCommentId(String commentId);
}