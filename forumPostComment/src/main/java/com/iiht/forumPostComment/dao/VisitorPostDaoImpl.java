package com.iiht.forumPostComment.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.forumPostComment.dto.VisitorPostDto;
import com.iiht.forumPostComment.model.VisitorPosts;
import com.iiht.forumPostComment.repository.PostRepository;

@Service
public class VisitorPostDaoImpl implements VisitorPostDao
{
	@Autowired
	private PostRepository repository; 
	//--------------------------------------------------------------------------------
	public Boolean saveUpdate(VisitorPostDto postInput, String userId, String loginName, String category) { 
		
		VisitorPosts visitorPosts = new VisitorPosts();
		
		visitorPosts.setId(postInput.getId());

		visitorPosts.setUserId(userId);
		visitorPosts.setLoginName(loginName);
		visitorPosts.setCategory(category);

		visitorPosts.setTitle(postInput.getTitle());
		visitorPosts.setTags(postInput.getTags());
		visitorPosts.setPostInfo(postInput.getPostInfo());

		repository.insert(visitorPosts);
		return Boolean.TRUE;
	}
	//--------------------------------------------------------------------------------
	public Boolean deleteVisitorPost(String postId) {
		repository.deleteById(postId);
		return true;
	};
	//--------------------------------------------------------------------------------
	public VisitorPostDto getPostById(String postId) {
		Optional<VisitorPosts> list = repository.findById(postId);
		return getVisitorPost(list.get());
	}
	//--------------------------------------------------------------------------------
	public List<VisitorPostDto> getAllPosts() {
		List<VisitorPosts> posts = repository.findAll();
		
		if(CollectionUtils.isEmpty(posts))
			return null;
		else
			return posts.stream().map(this::getVisitorPostDto).collect(Collectors.toList());
	}

	// SEARCH OPERATIONS
	//--------------------------------------------------------------------------------
	// Search by CATEGORY
	//--------------------------------------------------------------------------------
	public List<VisitorPostDto> getAllPostsByCategory(String category)
	{
		List<VisitorPosts> posts = repository.findPostsByCategory(category);
		
		if(CollectionUtils.isEmpty(posts))
			return null;
		else
			return posts.stream().map(this::getVisitorPostDto).collect(Collectors.toList());
		
	};
	//--------------------------------------------------------------------------------
	// Search by TITLE
	//--------------------------------------------------------------------------------
	public List<VisitorPostDto> getAllPostsByTitle(String title){

		List<VisitorPosts> posts = repository.findPostsByTitle(title);
		
		if(CollectionUtils.isEmpty(posts))
			return null;
		else
			return posts.stream().map(this::getVisitorPostDto).collect(Collectors.toList());
	};
	//--------------------------------------------------------------------------------
	// Search by TAGS
	//--------------------------------------------------------------------------------
	public List<VisitorPostDto> getAllPostsByTag(String tag){

		List<VisitorPosts> posts = repository.findPostsByTags(tag);
		
		if(CollectionUtils.isEmpty(posts))
			return null;
		else
			return posts.stream().map(this::getVisitorPostDto).collect(Collectors.toList());
	};

	//--------------------------------------------------------------------------------
	// Conversion Support Operations
	//--------------------------------------------------------------------------------
	public VisitorPostDto getVisitorPost(VisitorPosts posts) {
		return new VisitorPostDto(posts.getId(), posts.getUserId(), posts.getLoginName(), posts.getCategory(), posts.getTitle(), posts.getTags(), posts.getPostInfo());
	}
	//--------------------------------------------------------------------------------
	public VisitorPostDto getVisitorPostDto(VisitorPosts posts) {
		return new VisitorPostDto(posts.getId(), posts.getUserId(), posts.getLoginName(), posts.getCategory(), posts.getTitle(), posts.getTags(), posts.getPostInfo());
	}
}