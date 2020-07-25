package com.iiht.forumAdmin.model;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	@Id
	private String id;
	private String adminName;
	private String category;
}