package com.iiht.forumPostComment.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorLikeDto 
{
	private String id;

	@NotNull
	@Length(min = 1, max = 100)
	private String userId;

	@NotNull
	@Length(min = 1, max = 100)
	private String loginName;

	private String postId;

	private String commentId;

	@NotNull
	@Length(min = 1, max = 100)
	private String likeInfo;
}
