package com.iiht.forumAdmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.forumAdmin.dao.CategoryDao;
import com.iiht.forumAdmin.dto.CategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public Boolean addCategory(CategoryDto categoryInput) {
		return categoryDao.addCategory(categoryInput);
	};
	
	public Boolean deleteCategory(String categoryId) {
		return categoryDao.deleteCategory(categoryId);
	};
	
	public CategoryDto getCategoryById(String categoryId) {
		return categoryDao.getCategoryById(categoryId);
	};
	
	public List<CategoryDto> getAllCategories() {
		return categoryDao.getAllCategories();
	};
}