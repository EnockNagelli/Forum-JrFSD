package com.iiht.forumPostComment.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("LikeData")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorLikes {
	@Id
	private String id;
	private String postId;
	private String commentId;
	private String likeInfo;
}