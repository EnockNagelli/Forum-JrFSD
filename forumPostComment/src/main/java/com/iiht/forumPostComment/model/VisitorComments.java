package com.iiht.forumPostComment.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("CommentData")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorComments {
	@Id
	private String id;
	private String userId;
	private String loginName;
	private String postId;
	private String category;
	private String tags;
	private String commentInfo;
}