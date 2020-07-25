package com.iiht.forumPostComment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iiht.forumPostComment.model.VisitorPosts;

@Repository
public interface PostRepository extends MongoRepository<VisitorPosts, String>
{
	public VisitorPosts findPostById(String postId);
}