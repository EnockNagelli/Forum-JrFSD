package com.iiht.forumPostComment.dto;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorPostDto 
{
	private String id;

	@NotNull
	@Length(min = 1, max = 100)
	private String category;

	@NotNull
	@Length(min = 1, max = 100)
	private String title;
	
	@NotNull
	@Length(min = 1, max = 100)
	private String tags;

	@NotNull
	@Length(min = 1, max = 500)
	private String postInfo;
}