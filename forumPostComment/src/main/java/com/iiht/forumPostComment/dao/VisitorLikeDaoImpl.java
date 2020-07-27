package com.iiht.forumPostComment.dao;

import java.util.List;
import java.util.stream.Collectors;

//import javax.transaction.Transactional;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.forumPostComment.dto.VisitorLikeDto;
import com.iiht.forumPostComment.model.VisitorLikes;
import com.iiht.forumPostComment.repository.LikeRepository;

@Service
public class VisitorLikeDaoImpl implements VisitorLikeDao 
{
	@Autowired
	private LikeRepository likeRepository; 
	//--------------------------------------------------------------------------------------
	public Boolean saveUpdate(VisitorLikeDto likeInput) {

		VisitorLikes visitorLikes = new VisitorLikes();
		
		visitorLikes.setId(likeInput.getId());
		visitorLikes.setPostId(likeInput.getPostId());
		visitorLikes.setCommentId(likeInput.getCommentId());
		visitorLikes.setLikeInfo(likeInput.getLikeInfo());
		
		likeRepository.save(visitorLikes);
		return Boolean.TRUE;
	}
	//--------------------------------------------------------------------------------------
	public Boolean deleteLikeById(String likeId) {
		likeRepository.deleteById(likeId);
		return true;
	};
	//--------------------------------------------------------------------------------------
	public List<VisitorLikeDto> getVisitorLikeByPostId(String postId)
	{
		List<VisitorLikes> likes = likeRepository.findVisitorLikeByPostId(postId);
		
		if(CollectionUtils.isEmpty(likes))
			return null;
		else
			return likes.stream().map(this::getVisitorLikeDto).collect(Collectors.toList());
	}
	//--------------------------------------------------------------------------------------
	public List<VisitorLikeDto> getVisitorLikeByCommentId(String commentId)
	{
		List<VisitorLikes> likes = likeRepository.findVisitorLikeByCommentId(commentId);
		
		if(CollectionUtils.isEmpty(likes))
			return null;
		else
			return likes.stream().map(this::getVisitorLikeDto).collect(Collectors.toList());
	}	
	//--------------------------------------------------------------------------------------
	public List<VisitorLikeDto> getAllVisitorLikes() {
		
		List<VisitorLikes> likes = likeRepository.findAll();
		
		if(CollectionUtils.isEmpty(likes))
			return null;
		else
			return likes.stream().map(this::getVisitorLikeDto).collect(Collectors.toList());
	};

	//-------------------------------------------------------------------------------------------
	public VisitorLikeDto getVisitorLikeDto(VisitorLikes likes) {
		return new VisitorLikeDto(likes.getId(), likes.getPostId(), likes.getCommentId(), likes.getLikeInfo());
	}
}