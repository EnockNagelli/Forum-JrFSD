package com.iiht.forumPostComment.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("PostData")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorPosts {
	@Id
	private String id;
	private String category;
	private String title;
	private String tags;
	private String postInfo;
}