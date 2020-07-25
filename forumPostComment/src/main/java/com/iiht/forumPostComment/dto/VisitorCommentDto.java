package com.iiht.forumPostComment.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorCommentDto
{
	private String id;

	private String postId;

	@NotNull
	@Length(min = 1, max = 100)
	private String tags;
	
	@NotNull
	@Length(min = 1, max = 500)
	private String commentInfo;
}