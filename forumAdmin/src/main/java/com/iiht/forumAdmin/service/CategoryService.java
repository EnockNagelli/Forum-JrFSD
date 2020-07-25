package com.iiht.forumAdmin.service;

import java.util.List;

import com.iiht.forumAdmin.dto.CategoryDto;

public interface CategoryService {
	public Boolean addCategory(CategoryDto categoryInput); 
	public Boolean deleteCategory(String categoryId); 
	public CategoryDto getCategoryById(String categoryId);
	public List<CategoryDto> getAllCategories();
}
