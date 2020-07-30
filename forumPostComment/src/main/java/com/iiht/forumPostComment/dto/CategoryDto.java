package com.iiht.forumPostComment.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	@Id
	private String id;

	@NotNull
	@Length(min = 1, max = 100)
	private String adminName;

	@NotNull
	@Length(min = 1, max = 100)
	private String category;
}