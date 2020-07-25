package com.iiht.forumAdmin.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iiht.forumAdmin.dto.CategoryDto;
import com.iiht.forumAdmin.model.Category;
import com.iiht.forumAdmin.repository.CategoryRepository;

@Service
public class CategoryDaoImpl implements CategoryDao 
{
	@Autowired
	private CategoryRepository categoryRepository;
	//-----------------------------------------------------------------------------------------------
	public Boolean addCategory(CategoryDto categoryInput) {
		
		Category category = new Category();
		
		category.setId(categoryInput.getId());
		category.setAdminName(categoryInput.getAdminName());
		category.setCategory(categoryInput.getCategory());
		
		categoryRepository.insert(category);
		return Boolean.TRUE;
	}; 
	//-----------------------------------------------------------------------------------------------
	public Boolean deleteCategory(String id) {
		categoryRepository.deleteById(id);
		return true;
	};
	//-----------------------------------------------------------------------------------------------
	public CategoryDto getCategoryById(String categoryId) {
		return getCategoryObject(categoryRepository.findById(categoryId));
	};
	//-----------------------------------------------------------------------------------------------
	public List<CategoryDto> getAllCategories() {
		List<Category> posts = categoryRepository.findAll();
		
		if(CollectionUtils.isEmpty(posts))
			return null;
		else
			return posts.stream().map(this::getCategoryDto).collect(Collectors.toList());		
	};
	//-----------------------------------------------------------------------------------------------
	public CategoryDto getCategoryDto(Category category) {
		return new CategoryDto(category.getId(), category.getAdminName(), category.getCategory());
	}
	//-----------------------------------------------------------------------------------------------
	public CategoryDto getCategoryObject(Optional<Category> optional) {
		Category category = (Category) optional.get();
		return new CategoryDto(category.getId(), category.getAdminName(), category.getCategory());
	}
}