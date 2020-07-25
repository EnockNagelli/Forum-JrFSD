package com.iiht.forumPostComment.dao;

import java.util.List;
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
	public Boolean saveUpdate(VisitorPostDto postInput) {
		
		VisitorPosts visitorPosts = new VisitorPosts();
		
		visitorPosts.setId(postInput.getId());
		visitorPosts.setCategory(postInput.getCategory());
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
		return getVisitorPost(repository.findPostById(postId));
	}
	//--------------------------------------------------------------------------------
	public List<VisitorPostDto> getAllPosts() {
		List<VisitorPosts> posts = repository.findAll();
		
		if(CollectionUtils.isEmpty(posts))
			return null;
		else
			return posts.stream().map(this::getVisitorPostDto).collect(Collectors.toList());
	}
	//--------------------------------------------------------------------------------
	public VisitorPostDto getVisitorPost(VisitorPosts posts) {
		return new VisitorPostDto(posts.getId(), posts.getCategory(), posts.getTitle(), posts.getTags(), posts.getPostInfo());
	}
	//--------------------------------------------------------------------------------
	public VisitorPostDto getVisitorPostDto(VisitorPosts posts) {
		return new VisitorPostDto(posts.getId(), posts.getCategory(), posts.getTitle(), posts.getTags(), posts.getPostInfo());
	}
}