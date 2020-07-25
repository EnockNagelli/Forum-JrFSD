package com.iiht.forumAdmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.forumAdmin.dto.CategoryDto;
import com.iiht.forumAdmin.service.CategoryService;

@RestController
public class AdminController 														// PORT: 8091
{
	@Autowired
	private CategoryService categoryService;
	//-------------------------------------------------------------------------------------------
	@RequestMapping (value = "/")													// 1. WORKING
 	public String home () {
 		return "Forum Admin application";
 	}
	//-------------------------------------------------------------------------------------------
	@PostMapping(value="/postCategory")												// 2. WORKING	
	public ResponseEntity<Boolean> saveUpdate(@RequestBody CategoryDto categoryDto) {
		Boolean value = categoryService.addCategory(categoryDto);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//-------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/deleteCategory/{id}")									// 3. WORKING
	public ResponseEntity<Boolean> deleteCategory(@PathVariable String id) {
		Boolean value = categoryService.deleteCategory(id);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//-------------------------------------------------------------------------------------------
	@GetMapping(value = "/getCategoryById/{categoryId}")							// 4. WORKING
	public ResponseEntity<CategoryDto> getCommentById(@PathVariable String categoryId) {
		return new ResponseEntity<CategoryDto>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
	}
	//-------------------------------------------------------------------------------------------
	@GetMapping(value = "/getAllCategories", produces = "application/json")			// 5. WORKING
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategories(), HttpStatus.OK);
	}	
}