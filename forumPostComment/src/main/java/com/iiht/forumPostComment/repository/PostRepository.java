package com.iiht.forumPostComment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iiht.forumPostComment.model.VisitorPosts;

@Repository
public interface PostRepository extends MongoRepository<VisitorPosts, String>
{
	//public Optional<VisitorPosts> findById(String postId);

	public List<VisitorPosts> findPostsByCategory(String category);
	
	public List<VisitorPosts> findPostsByTitle(String title);

	public List<VisitorPosts> findPostsByTags(String tags);
}