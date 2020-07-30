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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;

import com.iiht.forumAdmin.dto.CategoryDto;
import com.iiht.forumAdmin.service.CategoryService;
//import com.netflix.discovery.EurekaClient;

@RestController
public class AdminController
{
	@Autowired
	private CategoryService categoryService;
	
//	@Autowired
//	private RestTemplate restTemplate;

//	@Autowired
//  private EurekaClient eurekaClient;
	
	//---------------------------------------------------------------------------------------------------
	// SERVICE OPERATIONS
	//---------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/", method = RequestMethod.GET)								// 1. WORKING
 	public String landingPage() {
 		return "Welcome to Forum Application - Admin Service.";
 	}
	//---------------------------------------------------------------------------------------------------
	@RequestMapping (value = "/admin", method = RequestMethod.GET)							// 2. WORKING
 	public String homePage() {
 		return "Welcome to Forum Application - Admin Service : This is an Admin privilege to add 'Categories'.";
 	}
	//---------------------------------------------------------------------------------------------------
	@PostMapping(value="/admin/addCategory")												// 3. WORKING	
	public ResponseEntity<Boolean> addNewCategory(@RequestBody CategoryDto categoryDto) {
		Boolean value = categoryService.addCategory(categoryDto);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//---------------------------------------------------------------------------------------------------
	@DeleteMapping(value = "/admin/deleteCategory/{id}")									// 4. WORKING
	public ResponseEntity<Boolean> deleteCategory(@PathVariable String id) {
		Boolean value = categoryService.deleteCategory(id);
		if (value) {
			return new ResponseEntity<Boolean>(value, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(value, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/admin/getCategoryById/{categoryId}")								// 5. WORKING
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable String categoryId) {
		return new ResponseEntity<CategoryDto>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------------------
	@GetMapping(value = "/admin/getAllCategories", produces = "application/json")			// 6. WORKING
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategories(), HttpStatus.OK);
	}
	
	//---------------------------------------------------------------------------------------------------
	// SEARCH OPERATIONS
	//---------------------------------------------------------------------------------------------------
//	@GetMapping(value = "/search/category/{categoryId}", produces = "application/json")		// 1. WORKING
//	public ResponseEntity<CategoryDto> searchCategoryById(@PathVariable String categoryId) {
//		return new ResponseEntity<CategoryDto>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
//	}
}